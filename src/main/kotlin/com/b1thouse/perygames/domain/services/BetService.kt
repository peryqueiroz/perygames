package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.application.web.dto.BetDTO
import com.b1thouse.perygames.domain.entities.*
import com.b1thouse.perygames.domain.entities.enums.BetResult
import com.b1thouse.perygames.domain.entities.enums.BetStatus
import com.b1thouse.perygames.domain.entities.enums.TransactionType
import com.b1thouse.perygames.domain.entities.enums.UserStatus
import com.b1thouse.perygames.domain.entities.external.AnonymousResponse
import com.b1thouse.perygames.domain.entities.external.LastMatchResponse
import com.b1thouse.perygames.domain.entities.external.MatchInfoResponse
import com.b1thouse.perygames.domain.exceptions.BetAlreadyPendingException
import com.b1thouse.perygames.domain.exceptions.InsufficientBalanceException
import com.b1thouse.perygames.domain.exceptions.PrivateProfileException
import com.b1thouse.perygames.domain.exceptions.UserNotActiveException
import com.b1thouse.perygames.domain.gateways.BetDetailStorageGateway
import com.b1thouse.perygames.domain.gateways.BetStorageGateway
import com.b1thouse.perygames.domain.services.external.StratzService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BetService(
    private val betStorageGateway: BetStorageGateway,
    private val betDetailStorageGateway: BetDetailStorageGateway,
    private val userService: UserService,
    private val redisService: RedisService,
    private val stratzService: StratzService,
    private val playerService: PlayerService,
    private val matchService: MatchService,
) {
    fun create(betDTO: BetDTO) {
        val user = userService.getById(betDTO.userId)

        if(!isUserActive(user)) throw UserNotActiveException()
        if(isPlayerAccountPrivate(user)) throw PrivateProfileException()
        if(!isBalanceValidForBet(user, betDTO)) throw InsufficientBalanceException()
        if(hasPendingBet(betDTO.userId)) throw BetAlreadyPendingException()

        val bet = Bet.fromDTO(betDTO)

        betStorageGateway.create(bet).let { betSaved ->
            betDTO.bet.forEach { betSubtype ->
                betDetailStorageGateway.create(BetDetail(betId = betSaved.id, betSubtypeId = betSubtype.subtypeId))
            }
            userService.debit(betDTO.userId, betDTO.amountBet, type = TransactionType.DEBIT_BET, betId = betSaved.id)
            saveOnCache(user, betSaved.id)
        }

        betStorageGateway.update(bet.copy(status = BetStatus.PENDING))

    }

    fun checkAllBetPendingToComplete() {
        val steamIdsBet = redisService.getAllKeys()
        if(!steamIdsBet.isNullOrEmpty()) {
            logger.info("Bet pending found on cache steamIds=$steamIdsBet")
            val steamIdsBetFormated = steamIdsBet.joinToString(",")
            val responseApi = stratzService.executeQuery(
                    "{players(steamAccountIds:[$steamIdsBetFormated]){steamAccount{isAnonymous} steamAccountId matches(request:{take:1}){id}}}",
                LastMatchResponse::class.java
            )
            responseApi?.data?.players?.forEach {
                if(!it.steamAccount?.isAnonymous.toBoolean()) {
                    val betCache = redisService.getValue(it.steamAccountId!!)
                    val lastMatchFromApi = it.matches.first().id.toString()
                    if (lastMatchFromApi != betCache?.lastMatchId) {
                        logger.info("New match has found to bet pending steamId=${it.steamAccountId} match=$lastMatchFromApi")
                        redisService.deleteKey(it.steamAccountId)
                        completeBet(betCache?.betId!!, lastMatchFromApi, it.steamAccountId)
                    } else {
                        logger.info("Same match for steamId ${it.steamAccountId}")
                    }
                } else {
                    logger.info("Player ${it.steamAccountId} has blocked profile")
                    redisService.getValue(it.steamAccountId!!)?.let { betCache ->
                        cancelBet(betCache.betId)
                    } ?: logger.info("betId not found on redis. steamAccountId=${it.steamAccountId}")
                    throw PrivateProfileException()
                }
            }
        }
    }

    fun completeBet(betId: String, matchGameId: String, steamAccountId: String) {
        logger.info("Completing betId = $betId with matchGameId = $matchGameId")
        val bet = betStorageGateway.findById(betId)
        val query = "{match(id:$matchGameId){startDateTime, durationSeconds, players(steamAccountId:$steamAccountId) {matchId, kills, deaths, assists, imp, heroDamage, towerDamage, heroHealing, isVictory, award, hero{displayName}}}}"
        val user = userService.getById(bet?.userId!!)
        val matchStratz = stratzService.executeQuery(query, MatchInfoResponse::class.java)
        if(matchStratz == null) {
            logger.info("Match info is null from Stratz api response. betId=$betId matchGameId=$matchGameId user=${user.id}")
            // ToDo: retentar?
        }
        val match: Match = Match.fromMatchInfoResponse(matchStratz!!, user.playerId)
        if(bet.createdAt.isBefore(match.startDate)) {
            val matchCreated = matchService.create(match)
            val betDetail = betDetailStorageGateway.findByBetId(betId)
            val killAssist = match.kill?.plus(match.assist!!)
            var deaths = match.death
            if(deaths == 0) deaths += 1
            val playerKda = BigDecimal.valueOf(Math.floorDiv(killAssist!!, deaths!!).toLong())
            // && (playerKda > betDetail.first().)
            if (match.win) {
                logger.info("Bet $betId won. AmountWon=${bet.amountReturn}")
                betStorageGateway.update(bet.copy(status = BetStatus.FINISH, matchId = match.id, result = BetResult.WIN))
                userService.deposit(userId = bet.userId, amount = bet.amountReturn!!, transactionType = TransactionType.CREDIT_BET, betId = betId)
            } else {
                logger.info("Bet $betId lost.")
                betStorageGateway.update(bet.copy(status = BetStatus.FINISH, matchId = matchCreated.id, result = BetResult.LOSE))
            }
        } else {
            logger.info("Bet was created after game start. betId=$betId matchSteamId=$matchGameId")
            val betCache = BetCache(lastMatchId = match.gameId, betId = betId)
            redisService.save(steamAccountId, betCache)
            logger.info("New cache saved key=$steamAccountId value=$betCache")
        }
    }
    private fun hasPendingBet(userId: String): Boolean {
        return betStorageGateway.findByUserIdAndStatusIn(userId, BetStatus.getPendingStatus()).isNotEmpty()
    }

    private fun isBalanceValidForBet(user: UserBet, betDTO: BetDTO): Boolean {
        return user.balance >= betDTO.amountBet
    }

    private fun isUserActive(user: UserBet): Boolean {
        return user.status == UserStatus.ACTIVE
    }

    private fun saveOnCache(user: UserBet, betId: String) {
        val player = playerService.findById(user.playerId)
        val query = "{ player(steamAccountId: ${player.gameId}) { matches(request:  { take: 1 }) { id } } }"

         val responseApi = stratzService.executeQuery(query, LastMatchResponse::class.java)
        // val responseApi = LastMatch(Data(player = listOf(Player(matches = listOf(Match(id=112233))))))

        val lastMatchId = responseApi?.data?.player?.matches?.first()?.id.toString()
        if (lastMatchId.isEmpty()) {
            println("Last match of user=${user.id} is null")
            //ToDo: return Exception?
        } else {
            redisService.save(player.gameId, BetCache(lastMatchId = lastMatchId, betId = betId))
            logger.info("Saving on cache key: ${player.gameId} value: $lastMatchId")
        }
    }

    fun cancelBet(betId: String?) {
        betId?.let {
            betStorageGateway.findById(it)?.let { bet ->
                betStorageGateway.update(bet.copy(status = BetStatus.CANCELED))
                logger.info("Bet $betId changed to CANCELED")
            } ?: logger.info("Bet $betId can not be canceled!")
        }
    }

    fun isPlayerAccountPrivate(user: UserBet): Boolean {
        val player = playerService.findById(user.playerId)
        val response = stratzService.executeQuery("{player(steamAccountId:${player.gameId}){steamAccount {isAnonymous}}}", AnonymousResponse::class.java)
        val isAnonymous = response?.data?.player?.steamAccount?.isAnonymous.toBoolean()
        return isAnonymous
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BetService::class.java)
    }
}
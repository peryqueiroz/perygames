package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.application.web.dto.BetDTO
import com.b1thouse.perygames.domain.entities.Bet
import com.b1thouse.perygames.domain.entities.BetDetail
import com.b1thouse.perygames.domain.entities.UserBet
import com.b1thouse.perygames.domain.entities.enums.BetStatus
import com.b1thouse.perygames.domain.entities.enums.TransactionType
import com.b1thouse.perygames.domain.entities.enums.UserStatus
import com.b1thouse.perygames.domain.exceptions.BetAlreadyPendingException
import com.b1thouse.perygames.domain.exceptions.InsufficientBalanceException
import com.b1thouse.perygames.domain.exceptions.UserNotActiveException
import com.b1thouse.perygames.domain.gateways.BetDetailStorageGateway
import com.b1thouse.perygames.domain.gateways.BetStorageGateway
import org.springframework.stereotype.Service

@Service
class BetService(
    private val betStorageGateway: BetStorageGateway,
    private val betDetailStorageGateway: BetDetailStorageGateway,
    private val userService: UserService
) {
    fun create(betDTO: BetDTO) {
        val user = userService.getById(betDTO.userId)

        if(!isUserActive(user)) throw UserNotActiveException()
        if(!isBalanceValidForBet(user, betDTO)) throw InsufficientBalanceException()
        if(hasPendingBet(betDTO.userId)) throw BetAlreadyPendingException()

        val bet = Bet.fromDTO(betDTO)

        betStorageGateway.create(bet).let { betSaved ->
            betDTO.bet.forEach { betSubtype ->
                betDetailStorageGateway.create(BetDetail(betId = betSaved.id, betSubtypeId = betSubtype.subtypeId))
            }
            userService.debit(betDTO.userId, betDTO.amountBet, type = TransactionType.DEBIT_BET, betId = betSaved.id)
        }

        betStorageGateway.update(bet.copy(status = BetStatus.PENDING))
    }

    fun hasPendingBet(userId: String): Boolean {
        return betStorageGateway.findByUserIdAndStatusIn(userId, BetStatus.getPendingStatus()).isNotEmpty()
    }

    fun isBalanceValidForBet(user: UserBet, betDTO: BetDTO): Boolean {
        return user.balance >= betDTO.amountBet
    }

    fun isUserActive(user: UserBet): Boolean {
        return user.status == UserStatus.ACTIVE
    }
}
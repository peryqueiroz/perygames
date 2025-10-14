package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.application.web.dto.CreatePlayerUserDTO
import com.b1thouse.perygames.domain.entities.Player
import com.b1thouse.perygames.domain.entities.TransactionRecord
import com.b1thouse.perygames.domain.entities.UserBet
import com.b1thouse.perygames.domain.entities.enums.TransactionType
import com.b1thouse.perygames.domain.exceptions.InvalidAmountException
import com.b1thouse.perygames.domain.exceptions.NotFoundException
import com.b1thouse.perygames.domain.gateways.UserStorageGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class UserService(
    private val userStorageGateway: UserStorageGateway,
    private val transactionRecordService: TransactionRecordService,
    private val playerService: PlayerService
) {
    fun create(createPlayerUserDTO: CreatePlayerUserDTO) {
        playerService.create(Player.fromCreatePlayerUser(createPlayerUserDTO)).let {
            userStorageGateway.create(UserBet.fromCreatePlayerUser(createPlayerUserDTO, it.id))
        }
    }

    fun getById(userId: String): UserBet {
        return userStorageGateway.findById(userId)?.also {
            logger.info("Found user by id=$userId")
        } ?: throw NotFoundException(message = "User not found by id=$userId").also {
            logger.info(it.message)
        }
    }

    fun deposit(userId: String, amount: BigDecimal, transactionType: TransactionType, betId: String? = null) {
        if(amount <= BigDecimal.ZERO) {
            throw InvalidAmountException(
                message = "Error while deposit balance for userId=$userId. Value cant be less than zero"
            )
        }
        val user = getById(userId)
        val finalBalance = user.balance.plus(amount)
        try {
            userStorageGateway.update(user.copy(balance = finalBalance, updatedAt = LocalDateTime.now()))
            makeTransactionRecord(userId, amount, transactionType, finalBalance = user.balance + amount, betId = betId)
            logger.info("Successfully deposited $amount reais to userId=$userId")
        } catch (ex: Exception) {
            logger.info("Error trying to deposit amount for userId=$userId. ex=${ex.message}" )
        }
    }

    fun debit(userId: String, amount: BigDecimal, type: TransactionType, description: String? = null, betId: String? = null) {
        getById(userId).let {
            val currentAmount = it.balance
            if (currentAmount >= amount) {
                val finalBalance = currentAmount.minus(amount)
                userStorageGateway.update(it.copy(balance = finalBalance, updatedAt = LocalDateTime.now()))
                makeTransactionRecord(userId, amount, type, finalBalance, description, betId = betId)
            }
        }
    }

    fun getByAccountId(accountId: String): UserBet? {
        logger.info("Finding user by accountId=$accountId")
        return userStorageGateway.findByAccountId(accountId)
    }

    private fun makeTransactionRecord(userId: String,
                              amount: BigDecimal,
                              type: TransactionType,
                              finalBalance: BigDecimal,
                              description: String? = null,
                              betId: String? = null) {
        transactionRecordService.create(TransactionRecord(
            userId = userId,
            transactionType = type,
            betId =  betId,
            amount = amount,
            description = description,
            balanceAfterTransaction = finalBalance,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        ))
    }

/*    fun withdraw(userId: String, request:  ): BigDecimal? {
        val user = userStorageGateway.findById(userId)
            ?: throw NotFoundException(message = "UserId $userId not found")

        if(amo)
        return user.takeIf { amount < it.balance }
            ?.let { userStorageGateway.withdrawBalance(userId, amount)
                    UserBet()
            }
            ?: throw InvalidAmountException(
                message = "Error while withdraw for userId=$userId. Value cant be more than balance. amount=$amount"
            )
    }
*/
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UserService::class.java)
    }
}
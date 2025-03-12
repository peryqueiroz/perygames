package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.domain.exceptions.InvalidAmountException
import com.b1thouse.perygames.domain.gateways.UserStorageGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AccountService(
    private val userStorageGateway: UserStorageGateway
) {
    fun deposit(userId: String, amount: BigDecimal) {
        if(amount <= BigDecimal.ZERO) {
            throw InvalidAmountException(
                message = "Error while deposit balance for userId=$userId. Value cant be less than zero"
            )
        }
        try {
            userStorageGateway.depositBalance(userId, amount)
            logger.info("Successfully deposited $amount reais to userId=$userId")
        } catch (ex: Exception) {
            logger.info("Error trying to deposit amount for userId=$userId. ex=${ex.message}" )
        }
    }

/*    fun withdraw(userId: String, request:  ): BigDecimal? {
        val user = userStorageGateway.getById(userId)
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
        private val logger: Logger = LoggerFactory.getLogger(AccountService::class.java)
    }
}
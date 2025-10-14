package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.UserBet
import com.b1thouse.perygames.domain.gateways.UserStorageGateway
import com.b1thouse.perygames.resources.storage.repository.UserRepository
import com.b1thouse.perygames.resources.storage.repository.toTable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class UserStorage(
    private val userRepository: UserRepository
) : UserStorageGateway {
    override fun create(user: UserBet): UserBet {
        return userRepository.save(user.toTable(new = true)).toDomain()
    }

    override fun update(user: UserBet): UserBet {
        return userRepository.save(user.toTable()).toDomain()
    }

    override fun findById(id: String): UserBet? {
        return userRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findByAccountId(accountId: String): UserBet? {
        return userRepository.findByAccountId(accountId)?.toDomain()
    }

    override fun depositBalance(userId: String, amount: BigDecimal) {
        return userRepository.depositBalance(userId, amount)
    }

    override fun withdrawBalance(userId: String, amount: BigDecimal) {
        return userRepository.withdrawBalance(userId, amount)
    }
}
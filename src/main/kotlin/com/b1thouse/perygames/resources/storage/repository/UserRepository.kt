package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.UserBet
import com.b1thouse.perygames.domain.entities.enums.UserStatus
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

interface UserRepository: CrudRepository<UserTable, String> {

    @Modifying
    @Transactional
    @Query("UPDATE user_bet set balance = balance + :amount WHERE id = :userId")
    fun depositBalance(userId: String, amount: BigDecimal)

    @Modifying
    @Transactional
    @Query("UPDATE user_bet ub set ub.balance = ub.balance - :amount WHERE ub.id = :userId")
    fun withdrawBalance(userId: String, amount: BigDecimal)

    fun findByAccountId(accountId: String): UserTable?
}


@Table(name = "user_bet")
data class UserTable(
    @Id private val id: String,
    val playerId: String,
    val status: UserStatus,
    val balance: BigDecimal,
    val accountId: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
): Persistable<String> {

    @Transient
    private var new: Boolean = false

    override fun getId() = id

    override fun isNew() = new

    constructor(user: UserBet, new: Boolean = false) : this (
    id = user.id,
    playerId = user.playerId,
    status = user.status,
    balance = user.balance,
    accountId = user.accountId,
    createdAt = user.createdAt,
    updatedAt = if (new) user.createdAt else LocalDateTime.now()
    ) {
        this.new = new
    }

    fun toDomain() = UserBet(
        id = id,
        playerId = playerId,
        status = status,
        balance = balance,
        accountId = accountId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun UserBet.toTable(new: Boolean = false) = UserTable(this, new)

package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.Bet
import com.b1thouse.perygames.domain.entities.enums.BetResult
import com.b1thouse.perygames.domain.entities.enums.BetStatus
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import java.math.BigDecimal
import java.time.LocalDateTime

interface BetRepository: CrudRepository<BetTable, String> {

    fun findByUserIdAndStatusIn(userId: String?, status: List<String>): List<BetTable>?
}

@Table(name = "bet")
data class BetTable(
    @Id private val id: String,
    val userId: String?,
    val matchId: String?,
    val status: BetStatus?,
    val amountBet: BigDecimal?,
    val amountReturn: BigDecimal?,
    val result: BetResult?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
): Persistable<String> {

    @Transient
    private var new: Boolean = false

    override fun getId() = id

    override fun isNew() = new

    constructor(bet: Bet, new: Boolean = false) : this (
        id = bet.id,
        userId = bet.userId,
        matchId = bet.matchId,
        status = bet.status,
        amountBet = bet.amountBet,
        amountReturn = bet.amountReturn,
        createdAt = bet.createdAt,
        result = bet.result,
        updatedAt = if (new) bet.createdAt else LocalDateTime.now()
    ) {
        this.new = new
    }

    fun toDomain() = Bet(
        id = id,
        userId = userId,
        matchId = matchId,
        status = status,
        amountBet = amountBet,
        amountReturn = amountReturn,
        result = result,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Bet.toTable(new: Boolean = false) = BetTable(this, new)
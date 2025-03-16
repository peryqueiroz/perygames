package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.Bet
import com.b1thouse.perygames.domain.entities.TransactionRecord
import com.b1thouse.perygames.domain.entities.enums.BetResult
import com.b1thouse.perygames.domain.entities.enums.BetStatus
import com.b1thouse.perygames.domain.entities.enums.TransactionType
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Transaction
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import java.math.BigDecimal
import java.time.LocalDateTime

interface TransactionRecordRepository: CrudRepository<TransactionRecordTable, String> {

}

@Table(name = "transaction_record")
data class TransactionRecordTable(
    @Id private val id: String,
    val userId: String,
    val betId: String?,
    val transactionType: TransactionType,
    val amount: BigDecimal,
    val balanceAfterTransaction: BigDecimal,
    val description: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
): Persistable<String> {

    @Transient
    private var new: Boolean = false

    override fun getId() = id

    override fun isNew() = new

    constructor(transactionRecord: TransactionRecord, new: Boolean = false) : this (
        id = transactionRecord.id,
        userId = transactionRecord.userId,
        betId = transactionRecord.betId,
        transactionType = transactionRecord.transactionType,
        amount = transactionRecord.amount,
        balanceAfterTransaction = transactionRecord.balanceAfterTransaction,
        description = transactionRecord.description,
        createdAt = transactionRecord.createdAt,
        updatedAt = if (new) transactionRecord.createdAt else LocalDateTime.now()
    ) {
        this.new = new
    }

    fun toDomain() = TransactionRecord(
        id = id,
        userId = userId,
        betId = betId,
        transactionType = transactionType,
        amount = amount,
        balanceAfterTransaction = balanceAfterTransaction,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun TransactionRecord.toTable(new: Boolean = false) = TransactionRecordTable(this, new)
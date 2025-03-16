package com.b1thouse.perygames.domain.entities

import com.b1thouse.perygames.domain.entities.enums.TransactionType
import de.huxhorn.sulky.ulid.ULID
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionRecord(
    val id:  String = ULID().nextULID(),
    val userId: String,
    val betId: String?,
    val transactionType: TransactionType,
    val amount: BigDecimal,
    val balanceAfterTransaction: BigDecimal,
    val description: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
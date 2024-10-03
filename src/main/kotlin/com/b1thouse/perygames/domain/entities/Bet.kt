package com.b1thouse.perygames.domain.entities

import com.b1thouse.perygames.domain.entities.enums.BetStatus
import de.huxhorn.sulky.ulid.ULID
import java.math.BigDecimal
import java.time.LocalDateTime

data class Bet(
    val id: String = ULID().nextULID(),
    val userId: String?,
    val matchId: String?,
    val status: BetStatus?,
    val amountBet: BigDecimal?,
    val amountReturn: BigDecimal?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
package com.b1thouse.perygames.domain.entities

import de.huxhorn.sulky.ulid.ULID
import java.math.BigDecimal
import java.time.LocalDateTime

data class BetType(
    val id: String = ULID().nextULID(),
    val type: String?,
    val subtype: String?,
    val odd: BigDecimal?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
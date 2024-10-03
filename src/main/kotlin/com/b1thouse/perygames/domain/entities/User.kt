package com.b1thouse.perygames.domain.entities

import com.b1thouse.perygames.domain.entities.enums.UserStatus
import de.huxhorn.sulky.ulid.ULID
import java.math.BigDecimal
import java.time.LocalDateTime

data class User(
    val id: String = ULID().nextULID(),
    val playerId: String?,
    val status: UserStatus,
    val email: String?,
    val password: String?,
    val balance: BigDecimal? = BigDecimal.ONE,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
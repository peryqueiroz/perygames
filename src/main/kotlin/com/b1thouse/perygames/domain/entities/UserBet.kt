package com.b1thouse.perygames.domain.entities

import com.b1thouse.perygames.application.web.dto.CreatePlayerUserDTO
import com.b1thouse.perygames.domain.entities.enums.UserStatus
import de.huxhorn.sulky.ulid.ULID
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserBet(
    val id: String = ULID().nextULID(),
    val playerId: String,
    val status: UserStatus,
    val balance: BigDecimal = BigDecimal.ZERO,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun fromCreatePlayerUser(createPlayerUserDTO: CreatePlayerUserDTO, playerId: String): UserBet {
            return UserBet(
                playerId = playerId,
                status = createPlayerUserDTO.status,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }
}
package com.b1thouse.perygames.domain.entities

import com.b1thouse.perygames.application.web.dto.BetDTO
import com.b1thouse.perygames.domain.entities.enums.BetStatus
import de.huxhorn.sulky.ulid.ULID
import java.math.BigDecimal
import java.time.LocalDateTime

data class Bet(
    val id: String = ULID().nextULID(),
    val userId: String?,
    val matchId: String? = null,
    val status: BetStatus? = BetStatus.CREATED,
    val amountBet: BigDecimal?,
    val amountReturn: BigDecimal?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object{
        fun fromDTO(betDTO: BetDTO): Bet {
            return Bet(
                userId = betDTO.userId,
                amountBet = betDTO.amountBet,
                amountReturn = betDTO.amountReturn,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }
}

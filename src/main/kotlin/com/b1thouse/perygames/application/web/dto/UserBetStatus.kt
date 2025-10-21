package com.b1thouse.perygames.application.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserBetStatus(
    @JsonProperty("hasBetPending", required = true) val hasBetPending : Boolean,
    @JsonProperty("currentBet") val currentBet : CurrentBet
)

data class CurrentBet(
    @JsonProperty("matchId") val matchId : String,
    @JsonProperty("amount") val amount : BigDecimal,
    @JsonProperty("odd") val odd : BigDecimal,
    @JsonProperty("createdAt") val createdAt : LocalDateTime,
    @JsonProperty("amountReturn") val amountReturn : BigDecimal
    )
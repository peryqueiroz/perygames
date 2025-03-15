package com.b1thouse.perygames.application.web.dto

import java.math.BigDecimal

data class BetDTO(
    val userId: String,
    val bet: List<BetDetailDTO>,
    val amountBet: BigDecimal,
    val amountReturn: BigDecimal,
    val oddFinal: BigDecimal
)

data class BetDetailDTO(
    val type: String,
    val subtypeId: String,
    val odd: BigDecimal
)
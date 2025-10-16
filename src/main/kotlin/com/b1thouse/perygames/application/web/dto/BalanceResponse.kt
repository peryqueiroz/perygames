package com.b1thouse.perygames.application.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class BalanceResponse(
    @JsonProperty("balance", required = true) val balance: BigDecimal,
)
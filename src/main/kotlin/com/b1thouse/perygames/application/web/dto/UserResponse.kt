package com.b1thouse.perygames.application.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class UserResponse(
    @JsonProperty("id", required = true) val id: String,
    @JsonProperty("balance") val balance: BigDecimal?,
)
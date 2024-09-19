package com.b1thouse.perygames.domain.exceptions

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
    @JsonProperty("type")
    val type: String? = null,
    @JsonProperty("message")
    val message: String? = null,
    @JsonProperty("details")
    val details: Any? = null
)
package com.b1thouse.perygames.domain.entities

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class BetCache @JsonCreator constructor(
    @JsonProperty("lastMatchId") val lastMatchId: String,
    @JsonProperty("betId") val betId: String
)
package com.b1thouse.perygames.domain.entities.external

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.Nulls


data class LastMatch(
    @JsonProperty("data") val data: Data
)

data class Data(
    @JsonProperty("player") val player: Player? = null,
    @JsonProperty("players") val players: List<Player>? = listOf()
)

data class Player(
    @JsonProperty("steamAccountId") val steamAccountId: String? = null,
    @JsonProperty("matches") val matches: List<Match>
)

data class Match(
    @JsonProperty("id") val id: Long
)
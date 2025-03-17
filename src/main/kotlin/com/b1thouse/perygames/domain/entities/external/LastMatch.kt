package com.b1thouse.perygames.domain.entities.external

import com.fasterxml.jackson.annotation.JsonProperty


data class LastMatch(
    @JsonProperty("data") val data: Data
)

data class Data(
    @JsonProperty("player") val player: Player
)

data class Player(
    @JsonProperty("matches") val matches: List<Match>
)

data class Match(
    @JsonProperty("id") val id: Long
)
package com.b1thouse.perygames.domain.entities.external

import com.fasterxml.jackson.annotation.JsonProperty


data class LastMatchResponse(
    @JsonProperty("data") val data: LastMatchData
)

data class LastMatchData(
    @JsonProperty("player") val player: LastMatchPlayer? = null,
    @JsonProperty("players") val players: List<LastMatchPlayer>? = listOf()
)

data class LastMatchPlayer(
    @JsonProperty("steamAccountId") val steamAccountId: String? = null,
    @JsonProperty("steamAccount") val steamAccount: LastMatchSteamAccount? = null,
    @JsonProperty("matches") val matches: List<LastMatch>
)

data class LastMatchSteamAccount(
    @JsonProperty("isAnonymous") val isAnonymous: String
)

data class LastMatch(
    @JsonProperty("id") val id: Long
)
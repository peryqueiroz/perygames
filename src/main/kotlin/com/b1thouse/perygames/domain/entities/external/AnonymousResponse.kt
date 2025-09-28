package com.b1thouse.perygames.domain.entities.external

import com.fasterxml.jackson.annotation.JsonProperty


data class AnonymousResponse(
    @JsonProperty("data") val data: AnonymousData
)

data class AnonymousData(
    @JsonProperty("player") val player: AnonymousPlayer
)

data class AnonymousPlayer(
    @JsonProperty("steamAccountId") val steamAccountId: AnonymousSteamAccount
)

data class AnonymousSteamAccount(
    @JsonProperty("isAnonymous") val isAnonymous: String
)
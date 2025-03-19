package com.b1thouse.perygames.domain.entities.external

import com.fasterxml.jackson.annotation.JsonProperty

data class MatchInfoResponse(
    @JsonProperty("data") val data: DataMatchInfo
)

data class DataMatchInfo(
    @JsonProperty("match") val match: MatchInfo
)

data class MatchInfo(
    @JsonProperty("startDateTime") val startDateTime: String,
    @JsonProperty("durationSeconds") val durationSeconds: String,
    @JsonProperty("players") val players: List<PlayersMatchInfo>
)


data class PlayersMatchInfo(
    @JsonProperty("steamAccountId") val steamAccountId: String,
    @JsonProperty("kills") val kills: String?,
    @JsonProperty("deaths") val deaths: String?,
    @JsonProperty("assists") val assists: String?,
    @JsonProperty("imp") val imp: String?,
    @JsonProperty("heroDamage") val heroDamage: String?,
    @JsonProperty("towerDamage") val towerDamage: String?,
    @JsonProperty("heroHealing") val heroHealing: String?,
    @JsonProperty("isVictory") val isVictory: String?,
    @JsonProperty("award") val award: String?,
    @JsonProperty("hero") val hero: HeroMatchInfo
)

data class HeroMatchInfo(
    @JsonProperty("displayName") val displayName: String?
)
package com.b1thouse.perygames.application.web.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PlayerResponse(
    @JsonProperty("id", required = true) val id: Long,
    @JsonProperty("game_id") val gameId: String?,
    @JsonProperty("name") val name: String,
)
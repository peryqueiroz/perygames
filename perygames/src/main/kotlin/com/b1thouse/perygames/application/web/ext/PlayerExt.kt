package com.b1thouse.perygames.application.web.ext

import com.b1thouse.perygames.application.web.dto.PlayerResponse
import com.b1thouse.perygames.domain.entities.Player

fun Player.toPlayerResponse() = PlayerResponse(
    id = id,
    gameId = gameId,
    name = name
)
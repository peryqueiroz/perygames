package com.b1thouse.perygames.application.web.ext

import com.b1thouse.perygames.application.web.dto.UserResponse
import com.b1thouse.perygames.domain.entities.UserBet

fun UserBet.toUserResponse() = UserResponse(
    id = id,
    balance = balance
)
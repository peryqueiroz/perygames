package com.b1thouse.perygames.application.web.dto

import com.b1thouse.perygames.domain.entities.enums.UserStatus

data class CreatePlayerUserDTO(
    val steamGameId: String,
    val name: String,
    val nick: String,
    val smurf: Boolean = false,
    val ranking: Int? = null,
    val medal: String? = null,
    val avatar: String? = null,
    val status: UserStatus = UserStatus.ACTIVE
)
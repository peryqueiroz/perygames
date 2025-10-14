package com.b1thouse.perygames.application.web.dto

data class LoginResponse(
    val token: String,
    val userId: String? = null,
    val accountId: String? = null
)
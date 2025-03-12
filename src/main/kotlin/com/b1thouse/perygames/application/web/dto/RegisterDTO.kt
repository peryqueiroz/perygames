package com.b1thouse.perygames.application.web.dto

import com.b1thouse.perygames.domain.entities.enums.UserRole


data class RegisterDTO(val login: String, val password: String, val role: UserRole)
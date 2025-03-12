package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.AuthUser
import org.springframework.security.core.userdetails.UserDetails

interface AccountStorageGateway {

    fun create(user: AuthUser)
    fun update(user: AuthUser): AuthUser
    fun getByLogin(login: String?): UserDetails?
}
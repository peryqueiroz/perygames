package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.User

interface UserStorageGateway {

    fun create(user: User): User
    fun update(user: User): User
    fun getById(id: String): User?
}
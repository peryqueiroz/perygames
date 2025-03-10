package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.UserBet

interface UserStorageGateway {

    fun create(user: UserBet): UserBet
    fun update(user: UserBet): UserBet
    fun getById(id: String): UserBet?
}
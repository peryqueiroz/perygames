package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.BetType

interface BetTypeStorageGateway {
    fun create(betType: BetType): BetType
    fun update(betType: BetType): BetType
    fun getById(id: String): BetType?
}
package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.Bet

interface BetStorageGateway {
    fun create(bet: Bet): Bet
    fun update(bet: Bet): Bet
    fun getById(id: String): Bet?
}
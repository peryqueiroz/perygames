package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.Bet
import com.b1thouse.perygames.domain.entities.enums.BetStatus

interface BetStorageGateway {
    fun create(bet: Bet): Bet
    fun update(bet: Bet): Bet
    fun findById(id: String): Bet?
    fun findByUserIdAndStatusIn(userId: String, status: List<BetStatus>): List<Bet>
}
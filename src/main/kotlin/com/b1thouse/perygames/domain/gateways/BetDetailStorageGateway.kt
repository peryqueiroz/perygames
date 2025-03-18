package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.BetDetail

interface BetDetailStorageGateway {

    fun create(betDetail: BetDetail): BetDetail

    fun findByBetId(betId: String): List<BetDetail>?
}
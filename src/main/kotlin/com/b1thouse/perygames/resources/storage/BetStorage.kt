package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.Bet
import com.b1thouse.perygames.domain.entities.enums.BetStatus
import com.b1thouse.perygames.domain.gateways.BetStorageGateway
import com.b1thouse.perygames.resources.storage.repository.BetRepository
import com.b1thouse.perygames.resources.storage.repository.toTable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BetStorage(
    private val betRepository: BetRepository
) : BetStorageGateway {
    override fun create(bet: Bet): Bet {
        return betRepository.save(bet.toTable(new = true)).toDomain()
    }

    override fun update(bet: Bet): Bet {
        return betRepository.save(bet.toTable()).toDomain()
    }

    override fun findById(id: String): Bet? {
        return betRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun findByUserIdAndStatusIn(userId: String, status: List<BetStatus>): List<Bet>? {
        return betRepository.findByUserIdAndStatusIn(userId, status.map { it.name })?.map { it.toDomain() }
    }
}
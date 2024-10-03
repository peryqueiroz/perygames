package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.BetType
import com.b1thouse.perygames.domain.gateways.BetTypeStorageGateway
import com.b1thouse.perygames.resources.storage.repository.BetTypeRepository
import com.b1thouse.perygames.resources.storage.repository.toTable
import org.springframework.data.repository.findByIdOrNull

class BetTypeStorage(
    private val betTypeRepository: BetTypeRepository
) : BetTypeStorageGateway {
    override fun create(betType: BetType): BetType {
        return betTypeRepository.save(betType.toTable(new = true)).toDomain()
    }

    override fun update(betType: BetType): BetType {
        return betTypeRepository.save(betType.toTable()).toDomain()
    }

    override fun getById(id: String): BetType? {
        return betTypeRepository.findByIdOrNull(id)?.toDomain()
    }
}
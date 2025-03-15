package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.BetDetail
import com.b1thouse.perygames.domain.gateways.BetDetailStorageGateway
import com.b1thouse.perygames.resources.storage.repository.BetDetailRepository
import com.b1thouse.perygames.resources.storage.repository.toTable
import org.springframework.stereotype.Component

@Component
class BetDetailStorage(
    private val betDetailRepository: BetDetailRepository
): BetDetailStorageGateway {
    override fun create(betDetail: BetDetail): BetDetail {
        return betDetailRepository.save(betDetail.toTable(new = true)).toDomain()
    }
}
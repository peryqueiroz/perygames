package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.application.web.dto.BetDTO
import com.b1thouse.perygames.domain.entities.Bet
import com.b1thouse.perygames.domain.entities.BetDetail
import com.b1thouse.perygames.domain.gateways.BetDetailStorageGateway
import com.b1thouse.perygames.domain.gateways.BetStorageGateway
import org.springframework.stereotype.Service

@Service
class BetService(
    private val betStorageGateway: BetStorageGateway,
    private val betDetailStorageGateway: BetDetailStorageGateway
) {
    fun create(betDTO: BetDTO) {

        betStorageGateway.create(Bet.fromDTO(betDTO)).let { betSaved ->
            betDTO.bet.forEach { betSubtype ->
                betDetailStorageGateway.create(BetDetail(betId = betSaved.id, betSubtypeId = betSubtype.subtypeId))
            }
        }
    }
}
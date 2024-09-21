package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.domain.entities.Player
import com.b1thouse.perygames.domain.exceptions.NotFoundException
import com.b1thouse.perygames.domain.gateways.PlayerStorageGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PlayerService(
    private val playerStorageGateway: PlayerStorageGateway
) {

    fun findById(id: Long): Player {
        return playerStorageGateway.getById(id)?.also {
            logger.info("Found player by id=$id")
        } ?: throw NotFoundException(message = "Player not found by id=$id").also {
            logger.info(it.message)
        }
    }

    fun findByGameId(gameId: String): Player? {
        return playerStorageGateway.getByGameId(gameId)?.also {
            logger.info("Found player by gameId=$gameId")
        } ?: throw NotFoundException(message = "Player not found by gameId=$gameId").also {
            logger.info(it.message)
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerService::class.java)
    }
}
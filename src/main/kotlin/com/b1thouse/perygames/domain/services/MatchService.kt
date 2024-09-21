package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.domain.entities.Match
import com.b1thouse.perygames.domain.exceptions.NotFoundException
import com.b1thouse.perygames.domain.gateways.MatchStorageGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MatchService(
    private val matchStorageGateway: MatchStorageGateway
) {
    fun findById(id: Long): Match {
        return matchStorageGateway.getById(id)?.also {
            logger.info("Found match by id=$id")
        } ?: throw NotFoundException(message = "Match not found by id=$id").also {
            logger.info(it.message)
        }
    }

    fun findByGameId(gameId: String): Match? {
        return matchStorageGateway.getByGameId(gameId)?.also {
            logger.info("Found match by gameId=$gameId")
        } ?: throw NotFoundException(message = "Match not found by gameId=$gameId").also {
            logger.info(it.message)
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MatchService::class.java)
    }
}
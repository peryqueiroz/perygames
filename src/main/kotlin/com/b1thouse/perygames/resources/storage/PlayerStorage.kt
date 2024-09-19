package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.Player
import com.b1thouse.perygames.domain.gateways.PlayerStorageGateway
import com.b1thouse.perygames.resources.storage.repository.PlayerRepository
import com.b1thouse.perygames.resources.storage.repository.toTable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PlayerStorage(
    private val playerRepository: PlayerRepository
) : PlayerStorageGateway {
    override fun create(player: Player): Player {
        logger.info("create $player")
        return playerRepository.save(player.toTable(new = true)).toDomain()
    }

    override fun update(player: Player): Player {
        logger.info("update $player")
        return playerRepository.save(player.toTable()).toDomain()
    }

    override fun getById(id: Long): Player? {
        logger.info("get player by id=$id")
        return playerRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun getByGameId(gameId: String): Player? {
        logger.info("get player by gameId=$gameId")
        return playerRepository.findByGameId(gameId)?.toDomain()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(PlayerStorage::class.java)
    }
}
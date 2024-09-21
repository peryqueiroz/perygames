package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.Match
import com.b1thouse.perygames.domain.gateways.MatchStorageGateway
import com.b1thouse.perygames.resources.storage.repository.MatchRepository
import com.b1thouse.perygames.resources.storage.repository.toTable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class MatchStorage(
    private val matchRepository: MatchRepository
) : MatchStorageGateway {
    override fun create(match: Match): Match {
        logger.info("create $match")
        return matchRepository.save(match.toTable(new = true)).toDomain()
    }

    override fun update(match: Match): Match {
        logger.info("update $match")
        return matchRepository.save(match.toTable()).toDomain()
    }

    override fun getById(id: String): Match? {
        logger.info("get match by id=$id")
        return matchRepository.findByIdOrNull(id)?.toDomain()
    }

    override fun getByGameId(gameId: String): Match? {
        logger.info("get match by gameId=$gameId")
        return matchRepository.findByGameId(gameId)?.toDomain()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MatchStorage::class.java)
    }
}
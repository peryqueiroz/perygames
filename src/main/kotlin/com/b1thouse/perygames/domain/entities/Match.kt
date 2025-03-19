package com.b1thouse.perygames.domain.entities

import com.b1thouse.perygames.domain.entities.enums.Award
import com.b1thouse.perygames.domain.entities.external.MatchInfoResponse
import de.huxhorn.sulky.ulid.ULID
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class Match(
    val id: String = ULID().nextULID(),
    val gameId: String,
    val playerId: String,
    val win: Boolean,
    val kill: Int?,
    val death: Int?,
    val assist: Int?,
    val award: Award?,
    val imp: Int?,
    val hero: String?,
    val startDate: LocalDateTime,
    val duration: Int?,
    val damageTower: String?,
    val damageHero: String?,
    val healing: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
){
    companion object{
        fun fromMatchInfoResponse(matchInfoResponse: MatchInfoResponse, playerId: String): Match {
            val matchInfo = matchInfoResponse.data.match.players.first()
            return Match(
                gameId = matchInfo.steamAccountId,
                playerId = playerId,
                win = matchInfo.isVictory.toBoolean(),
                kill = matchInfo.kills?.toInt(),
                death = matchInfo.deaths?.toInt(),
                assist = matchInfo.assists?.toInt(),
                award = Award.fromString(matchInfo.award),
                imp = matchInfo.imp?.toInt(),
                hero = matchInfo.hero.displayName,
                startDate = convertToLocalDateTime(matchInfoResponse.data.match.startDateTime.toLong()),
                duration = matchInfoResponse.data.match.durationSeconds.toInt(),
                damageTower = matchInfo.towerDamage,
                damageHero = matchInfo.heroDamage,
                healing = matchInfo.heroHealing,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }

        private fun convertToLocalDateTime(timestamp: Long): LocalDateTime {
            val instant = Instant.ofEpochSecond(timestamp)
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        }
    }
}


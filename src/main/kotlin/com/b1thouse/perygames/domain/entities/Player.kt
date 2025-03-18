package com.b1thouse.perygames.domain.entities

import com.b1thouse.perygames.application.web.dto.CreatePlayerUserDTO
import de.huxhorn.sulky.ulid.ULID
import java.time.LocalDateTime

data class Player(
    val id: String = ULID().nextULID(),
    val gameId: String,
    val name: String,
    val nick: String?,
    val smurf: Boolean? = null,
    val ranking: Int?,
    val medal: String? = null,
    val avatar: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun fromCreatePlayerUser(createPlayerUserDTO: CreatePlayerUserDTO): Player {
            return Player(
                gameId = createPlayerUserDTO.steamGameId,
                name = createPlayerUserDTO.name,
                nick = createPlayerUserDTO.nick,
                smurf = createPlayerUserDTO.smurf,
                ranking = createPlayerUserDTO.ranking,
                medal = createPlayerUserDTO.medal,
                avatar = createPlayerUserDTO.avatar,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }
}
package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.Player

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

interface PlayerRepository: CrudRepository<PlayerTable, String> {
    fun findByGameId(gameId: String?): PlayerTable?
}


@Table(name = "player")
data class PlayerTable(
    @Id private val id: String,
    val gameId: String?,
    val name: String,
    val nick: String?,
    val smurf: Boolean? = null,
    val ranking: Int?,
    val medal: String? = null,
    val avatar: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
): Persistable<String> {

    @Transient
    private var new: Boolean = false

    override fun getId() = id

    override fun isNew() = new

    constructor(player: Player, new: Boolean = false) : this (
        id = player.id,
        gameId = player.gameId,
        name = player.name,
        nick = player.nick,
        smurf = player.smurf,
        ranking = player.ranking,
        medal = player.medal,
        avatar = player.avatar,
        createdAt = player.createdAt,
        updatedAt = if (new) player.createdAt else LocalDateTime.now()
    ) {
        this.new = new
    }

    fun toDomain() = Player(
        id = id,
        gameId = gameId,
        name = name,
        nick = nick,
        smurf = smurf,
        ranking = ranking,
        medal = medal,
        avatar = avatar,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Player.toTable(new: Boolean = false) = PlayerTable(this, new)
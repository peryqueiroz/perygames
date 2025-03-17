package com.b1thouse.perygames.domain.entities

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
)
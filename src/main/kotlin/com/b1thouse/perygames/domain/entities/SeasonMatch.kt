package com.b1thouse.perygames.domain.entities

import de.huxhorn.sulky.ulid.ULID
import java.time.LocalDateTime

data class SeasonMatch(
    val id: String = ULID().nextULID(),
    val matchId: String?,
    val playerId: String?,
    val seasonId: String?,
    val score: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
package com.b1thouse.perygames.domain.entities

import de.huxhorn.sulky.ulid.ULID
import java.time.LocalDateTime

data class Match(
    val id: String = ULID().nextULID(),
    val gameId: String,
    val win: Boolean,
    val kill: Int,
    val death: Int,
    val assist: Int,
    val award: String?,
    val imp: String,
    val hero: String,
    val startDate: LocalDateTime,
    val duration: Int,
    val damageTower: String,
    val damageHero: String,
    val healing: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
package com.b1thouse.perygames.domain.entities

import de.huxhorn.sulky.ulid.ULID
import java.time.LocalDateTime

data class Season(
    val id: String = ULID().nextULID(),
    val version: String?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val active: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
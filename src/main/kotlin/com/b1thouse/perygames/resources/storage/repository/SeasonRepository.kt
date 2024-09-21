package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.Season
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

interface SeasonRepository: CrudRepository<SeasonTable, String>{}

@Table(name = "season")
data class SeasonTable(
    @Id private val id: String,
    val version: String?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val active: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
): Persistable<String> {

    @Transient
    private var new: Boolean = false

    override fun getId() = id

    override fun isNew() = new

    constructor(season: Season, new: Boolean = false) : this (
        id = season.id,
        version = season.version,
        startDate = season.startDate,
        endDate = season.endDate,
        active = season.active,
        createdAt = season.createdAt,
        updatedAt = if (new) season.createdAt else LocalDateTime.now()
    ) {
        this.new = new
    }

    fun toDomain() = Season(
        id = id,
        version = version,
        startDate = startDate,
        endDate = endDate,
        active = active,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Season.toTable(new: Boolean = false) = SeasonTable(this, new)
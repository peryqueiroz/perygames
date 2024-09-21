package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.Match
import com.b1thouse.perygames.domain.entities.enums.Award
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

interface MatchRepository: CrudRepository<MatchTable, String>{
    fun findByGameId(gameId: String?): MatchTable?
}

@Table(name = "match")
data class MatchTable(
    @Id private val id: String,
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
): Persistable<String> {

    @Transient
    private var new: Boolean = false

    override fun getId() = id

    override fun isNew() = new

    constructor(match: Match, new: Boolean = false) : this (
        id = match.id,
        gameId = match.gameId,
        win = match.win,
        kill = match.kill,
        death = match.death,
        assist = match.assist,
        award = match.award?.value,
        imp = match.imp,
        hero = match.hero,
        startDate = match.startDate,
        duration = match.duration,
        damageTower = match.damageTower,
        damageHero = match.damageHero,
        healing = match.healing,
        createdAt = match.createdAt,
        updatedAt = if (new) match.createdAt else LocalDateTime.now()
    ) {
        this.new = new
    }

    fun toDomain() = Match(
        id = id,
        gameId = gameId,
        win = win,
        kill = kill,
        death = death,
        assist = assist,
        award = award?.let { Award.valueOf(it) },
        imp = imp,
        hero = hero,
        startDate = startDate,
        duration = duration,
        damageTower = damageTower,
        damageHero = damageHero,
        healing = healing,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Match.toTable(new: Boolean = false) = MatchTable(this, new)
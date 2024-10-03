package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.BetType
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import java.math.BigDecimal
import java.time.LocalDateTime

interface BetTypeRepository: CrudRepository<BetTypeTable, String> {

}

@Table(name = "bet_type")
data class BetTypeTable(
    @Id private val id: String,
    val type: String?,
    val subtype: String?,
    val odd: BigDecimal?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
): Persistable<String> {

    @Transient
    private var new: Boolean = false

    override fun getId() = id

    override fun isNew() = new

    constructor(betType: BetType, new: Boolean = false) : this (
        id = betType.id,
        type = betType.type,
        subtype = betType.subtype,
        odd = betType.odd,
        createdAt = betType.createdAt,
        updatedAt = if (new) betType.createdAt else LocalDateTime.now()
    ) {
        this.new = new
    }

    fun toDomain() = BetType(
        id = id,
        type = type,
        subtype = subtype,
        odd = odd,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun BetType.toTable(new: Boolean = false) = BetTypeTable(this, new)
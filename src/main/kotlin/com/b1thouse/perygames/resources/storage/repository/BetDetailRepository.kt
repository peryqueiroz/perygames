package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.BetDetail
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository


interface BetDetailRepository: CrudRepository<BetDetailTable, String> {

}

@Table(name = "bet_detail")
data class BetDetailTable(
    @Id private val id: String,
    val betId: String,
    val betSubtypeId: String
): Persistable<String> {

    @Transient
    private var new: Boolean = false

    override fun getId() = id

    override fun isNew() = new

    constructor(betDetail: BetDetail, new: Boolean = false) : this (
        id = betDetail.id,
        betId = betDetail.betId,
        betSubtypeId = betDetail.betSubtypeId,
    ) {
        this.new = new
    }

    fun toDomain() = BetDetail(
        id = id,
        betId = betId,
        betSubtypeId = betSubtypeId,
    )
}

fun BetDetail.toTable(new: Boolean = false) = BetDetailTable(this, new)
package com.b1thouse.perygames.domain.entities.enums

enum class BetStatus {
    CANCELED,
    PENDING,
    FINISH,
    CREATED;

    fun isPending(): Boolean {
        return this == PENDING || this == CREATED
    }

    companion object {
        fun getPendingStatus(): List<BetStatus> {
            return entries.filter { it.isPending() }
        }
    }
}
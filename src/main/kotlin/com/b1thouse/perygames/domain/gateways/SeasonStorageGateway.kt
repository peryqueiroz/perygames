package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.Season

interface SeasonStorageGateway {

    fun create(season: Season): Season
    fun update(season: Season): Season
    fun getById(id: String): Season?
}
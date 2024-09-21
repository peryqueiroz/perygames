package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.Match

interface MatchStorageGateway {

    fun create(match: Match): Match
    fun update(match: Match): Match
    fun getById(id: String): Match?
    fun getByGameId(gameId: String): Match?
}
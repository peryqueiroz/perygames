package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.Player

interface PlayerStorageGateway {

    fun create(player: Player): Player
    fun update(player: Player): Player
    fun getById(id: String): Player?
    fun getByGameId(gameId: String): Player?
}
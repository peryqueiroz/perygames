package com.b1thouse.perygames.application.web.controller

import com.b1thouse.perygames.application.web.dto.PlayerResponse
import com.b1thouse.perygames.application.web.ext.toPlayerResponse
import com.b1thouse.perygames.domain.services.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("v1/player")
class PlayerController(
    private val playerService: PlayerService
) {

    @GetMapping("/{playerId}")
    fun getPlayerById(@PathVariable("playerId") playerId: Long): ResponseEntity<PlayerResponse> {
        return playerService.findById(playerId).let {
            val response = it.toPlayerResponse()
            ResponseEntity.status(HttpStatus.OK).body(response)
        }
    }
}
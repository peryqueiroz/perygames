package com.b1thouse.perygames.application.web.controller

import com.b1thouse.perygames.application.web.dto.BetDTO
import com.b1thouse.perygames.application.web.dto.UserBetStatus
import com.b1thouse.perygames.domain.services.BetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/bet")
class BetController(
    private val betService: BetService
) {
    @PostMapping
    fun create(@RequestBody betDTO: BetDTO) {
        betService.create(betDTO)
    }

    @GetMapping("/user/{userId}/bet-status")
    fun betStatus(@PathVariable("userId") userId: String) : ResponseEntity<UserBetStatus> {
        return ResponseEntity.ok(betService.hasPendingBet(userId))
    }
}
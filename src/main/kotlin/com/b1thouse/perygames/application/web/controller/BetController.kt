package com.b1thouse.perygames.application.web.controller

import com.b1thouse.perygames.application.web.dto.BetDTO
import com.b1thouse.perygames.domain.services.BetService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/bet")
class BetController(
    private val betService: BetService
) {
    @PostMapping
    fun create(@RequestBody betDTO: BetDTO) {
        betService.create(betDTO)
    }
}
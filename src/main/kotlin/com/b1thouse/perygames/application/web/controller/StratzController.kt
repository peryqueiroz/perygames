package com.b1thouse.perygames.application.web.controller

import com.b1thouse.perygames.domain.entities.external.LastMatch
import com.b1thouse.perygames.domain.services.external.StratzService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StratzController(
    private val stratzService: StratzService
) {

    @GetMapping("/stratz")
    fun stratzApi(): LastMatch? {
        val query = "{ player(steamAccountId: 150593805) { matches(request:  { take: 1 }) { id } } }"
        return stratzService.executeQuery(query, LastMatch::class.java)
    }
}
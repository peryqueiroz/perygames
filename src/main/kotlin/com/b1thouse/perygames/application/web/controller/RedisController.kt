package com.b1thouse.perygames.application.web.controller

import com.b1thouse.perygames.domain.services.RedisService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisController(private val redisService: RedisService) {

    @GetMapping("/set")
    fun setKey(@RequestParam key: String, @RequestParam value: String): String {
        redisService.save(key, value)
        return "Key $key saved with value $value"
    }

    @GetMapping("/get")
    fun getKey(@RequestParam key: String): String {
        val value = redisService.getValue(key)
        return value?.toString() ?: "Key not found"
    }
}
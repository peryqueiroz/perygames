package com.b1thouse.perygames.domain.services

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    fun save(key: String, value: Any, ttlInHour: Long) {
        redisTemplate.opsForValue().set(key, value, ttlInHour, TimeUnit.HOURS)
    }

    fun save(key: String, value: Any) {
        redisTemplate.opsForValue().set(key, value)
    }

    fun getValue(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }

    fun getAllKeys(): List<String>? {
        return redisTemplate.keys("*").toList()
    }

    fun deleteKey(key: String): Boolean {
        return redisTemplate.delete(key)
    }

    fun clearCache(): Long {
        val keys = redisTemplate.keys("*")
        return if (keys.isNotEmpty()) {
            redisTemplate.delete(keys)
        } else {
            0
        }
    }
}
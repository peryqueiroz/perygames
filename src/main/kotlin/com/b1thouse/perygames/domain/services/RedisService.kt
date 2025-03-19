package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.domain.entities.BetCache
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val objectMapper: ObjectMapper
) {

    fun save(key: String, value: Any, ttlInHour: Long) {
        val serializedBetCache = objectMapper.writeValueAsString(value)
        redisTemplate.opsForValue().set(key, serializedBetCache, ttlInHour, TimeUnit.HOURS)
    }

    fun save(key: String, value: BetCache) {
        val serializedBetCache = objectMapper.writeValueAsString(value)
        redisTemplate.opsForValue().set(key, serializedBetCache)
    }

    fun getValue(key: String): BetCache? {
        val serializedBetCache = redisTemplate.opsForValue().get(key) as String?
        return serializedBetCache?.let {
            objectMapper.readValue(it, BetCache::class.java)
        }
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
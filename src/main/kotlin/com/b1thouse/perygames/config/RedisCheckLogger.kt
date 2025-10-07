package com.b1thouse.perygames.config


import org.springframework.boot.CommandLineRunner
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.stereotype.Component

@Component
class RedisCheckLogger(private val factory: RedisConnectionFactory) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val lettuceFactory = factory as? LettuceConnectionFactory
        println("✅ Redis host: ${lettuceFactory?.hostName}")
        println("✅ Redis port: ${lettuceFactory?.port}")
        println("✅ Redis password: ${lettuceFactory?.password?.let { "******" } ?: "NENHUMA" }")
        println("✅ Redis SSL: ${lettuceFactory?.isUseSsl}")
    }
}

package com.b1thouse.perygames.application.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.text.SimpleDateFormat

@Configuration
class ObjectMapperConfiguration {

    @Bean
    @Primary
    fun defaultObjectMapper() = jacksonObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
        registerModules(JavaTimeModule())
        dateFormat = SimpleDateFormat("dd/MM/yyyy")
        configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
        this.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
    }
}
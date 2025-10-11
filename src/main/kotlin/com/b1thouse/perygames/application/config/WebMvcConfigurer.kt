package com.b1thouse.perygames.application.config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    @Bean
    fun forwardedHeaderFilter(): org.springframework.web.filter.ForwardedHeaderFilter {
        return org.springframework.web.filter.ForwardedHeaderFilter()
    }
}
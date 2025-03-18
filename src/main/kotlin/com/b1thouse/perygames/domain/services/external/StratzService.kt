package com.b1thouse.perygames.domain.services.external

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class StratzService(
    private val restTemplate: RestTemplate
) {
    @Value("\${stratz.api.token}")
    private lateinit var token: String

    @Value("\${stratz.api.url}")
    private lateinit var url: String

    fun <T> executeQuery(parametersQuery: String, responseType: Class<T>): T? {

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            setBearerAuth(token)
            set(HttpHeaders.USER_AGENT, "STRATZ_API")
            set("GraphQl-Require-Preflight", "true")
        }
        //val parametersQuery = "{ player(steamAccountId: 150593805) { matches(request:  { take: 1 }) { id } } }"
        val query = String.format("{ \"query\": \"query $parametersQuery\" }")

        val requestEntity = HttpEntity<String>(query, headers)
        return try {
            logger.info("Making request for Stratz request=$requestEntity")
            val responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                responseType
            )
            logger.info("Response Stratz= ${requestEntity.body}")
            responseEntity.body
        } catch (ex: Exception) {
            logger.info("Error on calling Stratz ex: $ex")
            throw ex
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(StratzService::class.java)
    }
}
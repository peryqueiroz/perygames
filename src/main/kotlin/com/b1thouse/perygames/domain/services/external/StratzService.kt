package com.b1thouse.perygames.domain.services.external

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
            val responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                responseType
            )

            responseEntity.body
        } catch (ex: Exception) {

            throw ex
        }

    }
}
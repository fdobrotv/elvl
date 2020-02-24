package com.fdobrotv.elvl.impl.integration

import com.fdobrotv.elvl.impl.controller.QuoteApiImpl
import com.fdobrotv.elvl.impl.util.quoteOne
import com.fdobrotv.elvl.model.QuoteIn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ElvlApiTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var apiImpl: QuoteApiImpl

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    @Throws(Exception::class)
    fun contexLoads() {
        assertThat(apiImpl).isNotNull
    }

    @Test
    @Throws(Exception::class)
    fun postQuoteShouldReturnState() {
        val headers = HttpHeaders()
        headers.add("content-type","application/json")
        val request: HttpEntity<QuoteIn> = HttpEntity(quoteOne.isin("RU100A0JX0J2"), headers)
        val response = restTemplate.postForEntity("http://localhost:$port/quotes", request,
                String::class.java)
        val body = response.body
        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    @Throws(Exception::class)
    fun shouldPassDocumentedTest() {
        val headers = HttpHeaders()
        headers.add("content-type","application/json")
        val request: HttpEntity<QuoteIn> = HttpEntity(quoteOne, headers)
        restTemplate.postForEntity("http://localhost:$port/quotes", request,
                String::class.java)

        val response = restTemplate.getForEntity("http://localhost:$port/elvls/${quoteOne.isin}",
                String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

}
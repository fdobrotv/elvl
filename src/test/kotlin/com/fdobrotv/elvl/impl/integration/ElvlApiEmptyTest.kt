package com.fdobrotv.elvl.impl.integration

import com.fdobrotv.elvl.impl.controller.QuoteApiImpl
import com.fdobrotv.elvl.impl.util.quoteOne
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import javax.transaction.Transactional

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ElvlApiEmptyTest {

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
    fun quotesShouldReturnEmptyArray() {
        assertThat(restTemplate.getForObject("http://localhost:$port/quotes",
                String::class.java)).contains("[]")
    }

    @Test
    @Throws(Exception::class)
    fun elvlsShouldReturnEmptyArray() {
        assertThat(this.restTemplate.getForObject("http://localhost:$port/elvls",
                String::class.java)).contains("[]")
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturn404ByIsin() {
        val response = restTemplate.getForEntity("http://localhost:$port/elvls/${quoteOne.isin}",
                String::class.java)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

}
package com.fdobrotv.elvl.impl.integration

import com.fdobrotv.elvl.impl.controller.QuoteApiImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort


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
    fun quotesShouldReturnEmptyArray() {
        assertThat(this.restTemplate.getForObject("http://localhost:$port/quotes",
                String::class.java)).contains("[]")
    }

    @Test
    @Throws(Exception::class)
    fun elvlsShouldReturnEmptyArray() {
        assertThat(this.restTemplate.getForObject("http://localhost:$port/elvls",
                String::class.java)).contains("[]")
    }

}
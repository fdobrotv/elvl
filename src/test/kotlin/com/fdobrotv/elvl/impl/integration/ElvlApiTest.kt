package com.fdobrotv.elvl.impl.integration

import com.fdobrotv.elvl.impl.controller.QuoteController
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
    private val controller: QuoteController? = null

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    @Throws(Exception::class)
    fun contexLoads() {
        assertThat(controller).isNotNull
    }

    @Test
    @Throws(Exception::class)
    fun greetingShouldReturnDefaultMessage() {
        assertThat(this.restTemplate?.getForObject("http://localhost:$port/quotes",
                String::class.java)).contains("[]")
    }

}
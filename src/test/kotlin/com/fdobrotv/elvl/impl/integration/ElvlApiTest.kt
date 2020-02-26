package com.fdobrotv.elvl.impl.integration

import com.fdobrotv.elvl.impl.controller.QuoteApiImpl
import com.fdobrotv.elvl.impl.util.quoteOne
import com.fdobrotv.elvl.impl.util.quoteThree
import com.fdobrotv.elvl.impl.util.quoteTwo
import com.fdobrotv.elvl.model.Quote
import com.fdobrotv.elvl.model.QuoteIn
import mu.KotlinLogging
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

private val logger = KotlinLogging.logger {}

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ElvlApiTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var apiImpl: QuoteApiImpl

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun contexLoads() {
        assertThat(apiImpl).isNotNull
    }

    @Test
    fun shouldUseBidIfElvlAbsent() {
        val headers = HttpHeaders()
        headers.add("content-type", "application/json")
        val quoteOneRequest: HttpEntity<QuoteIn> = HttpEntity(quoteOne.isin("RU120A0JX0J2"), headers)
        val quoteOneResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteOneRequest,
                Quote::class.java)

        assertEquals(HttpStatus.CREATED, quoteOneResponse.statusCode)
        logger.info { "first elvl is ${quoteOneResponse.body!!.elvl}" }
        assertEquals(quoteOne.bid, quoteOneResponse.body!!.elvl)
    }

    @Test
    fun postQuotesShouldShowSuccessElvls() {
        val headers = HttpHeaders()
        headers.add("content-type","application/json")
        val quoteOneRequest: HttpEntity<QuoteIn> = HttpEntity(quoteOne.isin("RU100A0JX0J2"), headers)
        val quoteOneResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteOneRequest,
                Quote::class.java)

        assertEquals(HttpStatus.CREATED, quoteOneResponse.statusCode)
        logger.info { "first elvl is ${quoteOneResponse.body!!.elvl}" }
        assertEquals(quoteOne.bid, quoteOneResponse.body!!.elvl)

        val quoteTwoRequest: HttpEntity<QuoteIn> = HttpEntity(quoteTwo.isin("RU100A0JX0J2"), headers)
        val quoteTwoResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteTwoRequest,
                Quote::class.java)

        logger.info { "second elvl is ${quoteTwoResponse.body!!.elvl}" }
        assertEquals(quoteTwo.bid, quoteTwoResponse.body!!.elvl)
    }

    @Test
    fun shouldUseAskAsElvlIfItBigger() {
        val headers = HttpHeaders()
        headers.add("content-type","application/json")
        val quoteOneRequest: HttpEntity<QuoteIn> = HttpEntity(quoteOne.isin("RU100A0JX0J2"), headers)
        val quoteOneResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteOneRequest,
                Quote::class.java)

        assertEquals(HttpStatus.CREATED, quoteOneResponse.statusCode)
        logger.info { "first elvl is ${quoteOneResponse.body!!.elvl}" }
        assertEquals(quoteOne.bid, quoteOneResponse.body!!.elvl)

        val quoteTwoRequest: HttpEntity<QuoteIn> = HttpEntity(quoteThree.isin("RU100A0JX0J2"), headers)
        val quoteTwoResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteTwoRequest,
                Quote::class.java)

        logger.info { "second elvl is ${quoteTwoResponse.body!!.elvl}" }
        assertEquals(quoteThree.ask, quoteTwoResponse.body!!.elvl)
    }

    @Test
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
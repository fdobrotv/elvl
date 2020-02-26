package com.fdobrotv.elvl.impl.integration

import com.fdobrotv.elvl.impl.controller.QuoteApiImpl
import com.fdobrotv.elvl.impl.util.bigDecimal
import com.fdobrotv.elvl.impl.util.quoteOne
import com.fdobrotv.elvl.model.Quote
import com.fdobrotv.elvl.model.QuoteIn
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ElvlApiTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var apiImpl: QuoteApiImpl

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun contextLoads() {
        assertThat(apiImpl).isNotNull
    }

    @Test
    fun shouldUseAskAsElvlIfBidAbsent() {
        val headers = HttpHeaders()
        headers.add("content-type", "application/json")
        val quoteOneRequest: HttpEntity<QuoteIn> = HttpEntity(quoteOne.isin("RU130A0JX0J2").bid(null), headers)
        val quoteOneResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteOneRequest,
                Quote::class.java)

        assertEquals(HttpStatus.CREATED, quoteOneResponse.statusCode)
        logger.info { "first elvl is ${quoteOneResponse.body!!.elvl}" }
        assertEquals(quoteOne.ask, quoteOneResponse.body!!.elvl)
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
        headers.add("content-type", "application/json")
        val quoteOneRequest: HttpEntity<QuoteIn> = HttpEntity(QuoteIn()
                .isin("RU000A0JX0J2")
                .bid(bigDecimal(100.2))
                .ask(bigDecimal(101.9)), headers)
        val quoteOneResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteOneRequest,
                Quote::class.java)

        assertEquals(HttpStatus.CREATED, quoteOneResponse.statusCode)
        logger.info { "first elvl is ${quoteOneResponse.body!!.elvl}" }
        assertEquals(bigDecimal(100.2), quoteOneResponse.body!!.elvl)

        val quoteTwoRequest: HttpEntity<QuoteIn> = HttpEntity(QuoteIn()
                .isin("RU000A0JX0J2")
                .bid(bigDecimal(100.5))
                .ask(bigDecimal(101.9)), headers)
        val quoteTwoResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteTwoRequest,
                Quote::class.java)

        logger.info { "second elvl is ${quoteTwoResponse.body!!.elvl}" }
        assertEquals(bigDecimal(100.5), quoteTwoResponse.body!!.elvl)
    }

    @Test
    fun shouldUseAskAsElvlIfItBigger() {
        val headers = HttpHeaders()
        headers.add("content-type", "application/json")
        val quoteOneRequest: HttpEntity<QuoteIn> = HttpEntity(QuoteIn()
                .isin("RU000A0JX0J2")
                .bid(bigDecimal(100.2))
                .ask(bigDecimal(101.9)), headers)
        val quoteOneResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteOneRequest,
                Quote::class.java)

        assertEquals(HttpStatus.CREATED, quoteOneResponse.statusCode)
        logger.info { "first elvl is ${quoteOneResponse.body!!.elvl}" }
        assertEquals(bigDecimal(100.2), quoteOneResponse.body!!.elvl)

        val quoteTwoRequest: HttpEntity<QuoteIn> = HttpEntity(QuoteIn()
                .isin("RU000A0JX0J2")
                .bid(bigDecimal(88.5))
                .ask(bigDecimal(100.1)), headers)
        val quoteTwoResponse = restTemplate.postForEntity("http://localhost:$port/quotes", quoteTwoRequest,
                Quote::class.java)

        logger.info { "second elvl is ${quoteTwoResponse.body!!.elvl}" }
        assertEquals(bigDecimal(100.1), quoteTwoResponse.body!!.elvl)
    }

    @Test
    fun shouldPassDocumentedTest() {
        val headers = HttpHeaders()
        headers.add("content-type", "application/json")
        val request: HttpEntity<QuoteIn> = HttpEntity(quoteOne, headers)
        restTemplate.postForEntity("http://localhost:$port/quotes", request,
                String::class.java)

        val response = restTemplate.getForEntity("http://localhost:$port/elvls/${quoteOne.isin}",
                String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

}
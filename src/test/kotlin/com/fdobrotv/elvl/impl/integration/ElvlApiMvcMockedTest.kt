package com.fdobrotv.elvl.impl.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fdobrotv.elvl.model.QuoteIn
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.Rollback
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal
import java.math.RoundingMode


@SpringBootTest
@AutoConfigureMockMvc
class ElvlApiMvcMockedTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @Rollback
    fun whenFirstValidInput_thenReturns201() {
        val firstQuote = QuoteIn()
                .isin("RU000A0JX0J2")
                .bid(bigDecimal(100.2))
                .ask(bigDecimal(101.9))

        mockMvc.perform(
                post("/quotes", objectMapper.writeValueAsString(firstQuote))
                        .contentType(MediaType.parseMediaType("application/json"))
                        .accept(MediaType.parseMediaType("application/json")
                        )
        )
                .andExpect(status().isCreated)
                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.quote[?(@.bid=='1.0')].bid").value("1.0"))
    }

    private fun bigDecimal(double: Double) = BigDecimal(double).setScale(2, RoundingMode.HALF_EVEN)

    @Test
    fun whenQuotesAreEmpty_thenReturns200() {
        mockMvc.perform(
                get("/quotes")
                        .accept(MediaType.parseMediaType("application/json")
                        )
        )
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json"))
    }

}
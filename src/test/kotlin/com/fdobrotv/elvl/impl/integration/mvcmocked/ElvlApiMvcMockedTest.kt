package com.fdobrotv.elvl.impl.integration.mvcmocked

import com.fasterxml.jackson.databind.ObjectMapper
import com.fdobrotv.elvl.api.QuotesApi
import com.fdobrotv.elvl.model.QuoteIn
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.math.RoundingMode
import javax.transaction.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ElvlApiMvcMockedTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun whenFirstValidInput_thenReturns201() {
        val firstQuote = QuoteIn()
                .isin("RU000A0JX0J2")
                .bid(bigDecimal(100.2))
                .ask(bigDecimal(101.9))

        val result: MvcResult = mockMvc.perform( MockMvcRequestBuilders
                .post("/quotes", asJsonString(firstQuote))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
//                .andExpect(status().isCreated)
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty);
        val content = result.response.contentAsString

        mockMvc.perform(
                post("/quotes", asJsonString(firstQuote))
                        .contentType(MediaType.parseMediaType("application/json"))
                        .accept(MediaType.parseMediaType("application/json"))
        )
//                .andDo(print())
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

    fun asJsonString(obj: Any): String {
        return try {
            objectMapper.writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
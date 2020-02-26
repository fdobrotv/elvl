package com.fdobrotv.elvl.impl.integration.mvcmocked

import com.fasterxml.jackson.databind.ObjectMapper
import com.fdobrotv.elvl.impl.util.bigDecimal
import com.fdobrotv.elvl.model.QuoteIn
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class InputValidationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun testWrongContentType_failsValidation() {
        mockMvc.perform(post("/quotes"))
                .andExpect(status().isUnsupportedMediaType)
                .andDo(print())
    }

    @Test
    fun testBidBiggerThanAsk_failsValidation() {
        val quote = QuoteIn().isin("RU000A0JX0J2")
                .bid(bigDecimal(120))
                .ask(bigDecimal(100.2))

        mockMvc.perform(
                post("/quotes").content(asJsonString(quote))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest)
    }

    @Test
    fun testIsinBiggerThan12Symbols_failsValidation() {
        val quote = QuoteIn().isin("RU000A0JX0J2222")
                .bid(bigDecimal(99))
                .ask(bigDecimal(100.2))

        mockMvc.perform(
                post("/quotes").content(asJsonString(quote))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest)
    }

    fun asJsonString(obj: Any): String {
        return try {
            objectMapper.writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
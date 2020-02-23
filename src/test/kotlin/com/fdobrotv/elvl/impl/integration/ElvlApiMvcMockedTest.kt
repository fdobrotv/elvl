package com.fdobrotv.elvl.impl.integration

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@SpringBootTest
@AutoConfigureMockMvc
class ElvlApiMvcMockedTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun whenValidInput_thenReturns200() {
        mockMvc.perform(
                get("/quotes")
                        .accept(MediaType.parseMediaType("application/json")
                        )
        )
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.quote[?(@.bid=='1.0')].bid").value("1.0"))
    }

}
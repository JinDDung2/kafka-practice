package com.example.kafka.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CheckoutSummitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postSummitCheckout() throws Exception {
        mockMvc.perform(post("/summitCheckout")
                        .param("memberId", "10001")
                        .param("productId", "200001")
                        .param("amount", "30000")
                        .param("shippingAddress", "542"))
                .andExpect(status().isOk())
                .andDo(print());
        
    }

}
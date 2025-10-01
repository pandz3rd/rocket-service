package com.pandz.rocket_service.controller;

import com.pandz.rocket_service.service.usecase.AstronoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AstronoutController.class)
@WebMvcTest(AstronoutController.class)
public class AstronoutControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AstronoutService astronoutService;

    @Test
    void displayResult_test() throws Exception {
        when(astronoutService.displayResult(any(), anyString())).thenReturn("*");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/astronout/display")
            .contentType(MediaType.APPLICATION_JSON)
            .param("type", "NORMAL"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
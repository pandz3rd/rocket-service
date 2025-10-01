package com.pandz.rocket_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pandz.rocket_service.model.api.rocket.PageRocketRes;
import com.pandz.rocket_service.model.api.rocket.RocketReq;
import com.pandz.rocket_service.model.api.rocket.RocketRes;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.service.usecase.RocketService;
import com.pandz.rocket_service.util.ResponseUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RocketController.class)
@WebMvcTest(RocketController.class)
public class RocketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RocketService rocketService;

    @Test
    void storeRocket_test() throws Exception {
        ResponseEntity<ApiBaseResponse<RocketRes>> result = ResponseUtil.buildHttpResponse(ResponseUtil.buildResponse("00", "Success", RocketRes.builder().build()));
        when(rocketService.storeRocket(any(), any())).thenReturn(result);

        RocketReq req = RocketReq.builder().name("Rocket Rocker").astronoutCap(3).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/rocket/store")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(req))
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void getListRocket_test() throws Exception {
        ResponseEntity<ApiBaseResponse<PageRocketRes>> result = ResponseUtil.buildHttpResponse(ResponseUtil.buildResponse("00", "Success", PageRocketRes.builder().build()));
        when(rocketService.getListRocket(any(), anyInt(), anyInt())).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/rocket/list")
            .contentType(MediaType.APPLICATION_JSON)
            .param("page", "0")
            .param("size", "10")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
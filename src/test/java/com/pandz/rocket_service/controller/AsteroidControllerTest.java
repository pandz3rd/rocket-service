package com.pandz.rocket_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pandz.rocket_service.model.api.asteroid.AsteroidReq;
import com.pandz.rocket_service.model.api.asteroid.AsteroidRes;
import com.pandz.rocket_service.model.api.asteroid.PageAsteroidRes;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.service.usecase.AsteroidService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AsteroidController.class)
@WebMvcTest(AsteroidController.class)
public class AsteroidControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AsteroidService asteroidService;

    @Test
    void storeAsteroid_test() throws Exception {
        ResponseEntity<ApiBaseResponse<AsteroidRes>> mockResult = ResponseUtil.buildHttpResponse(ResponseUtil.buildResponse("00", "Success", AsteroidRes.builder().build()));
        when(asteroidService.storeAsteroid(any(), any()))
            .thenReturn(mockResult);

        AsteroidReq req = AsteroidReq.builder().name("Planet Kaesang").isHazardous(true).build();

        mockMvc.perform(post("/v1/asteroid/store")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(req))
        )
        .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getListAsteroid_test() throws Exception {
        ResponseEntity<ApiBaseResponse<PageAsteroidRes>> mockResult = ResponseUtil.buildHttpResponse(ResponseUtil.buildResponse("00", "Success", PageAsteroidRes.builder().build()));
        when(asteroidService.getListAsteroid(any(), anyInt(), anyInt()))
            .thenReturn(mockResult);

        mockMvc.perform(get("/v1/asteroid/list")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("page", "0")
            .param("size", "10")
        )
        .andExpect(status().is2xxSuccessful());
    }
}
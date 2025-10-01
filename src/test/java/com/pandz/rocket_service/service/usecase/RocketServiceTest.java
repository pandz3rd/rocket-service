package com.pandz.rocket_service.service.usecase;

import com.pandz.rocket_service.exception.RocketException;
import com.pandz.rocket_service.model.api.asteroid.AsteroidReq;
import com.pandz.rocket_service.model.api.rocket.PageRocketRes;
import com.pandz.rocket_service.model.api.rocket.RocketReq;
import com.pandz.rocket_service.model.api.rocket.RocketRes;
import com.pandz.rocket_service.model.db.RocketDao;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.helper.RocketDataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RocketService.class)
public class RocketServiceTest {
    @Autowired
    private RocketService rocketService;

    @MockitoBean
    private RocketDataHelper rocketDataHelper;

    @Test
    void storeRocket_successTest() {
        RocketReq req = RocketReq.builder()
            .name("Rockey Gerung")
            .astronoutCap(3)
            .fuelTank(100)
            .listAsteroid(List.of(AsteroidReq.builder().name("Pororo").build()))
            .build();
        RocketDao mockResult = RocketDao.builder().name("Rockey Gerung").astronoutCap(3).fuelTank(100).build();
        when(rocketDataHelper.storeRocket(any())).thenReturn(mockResult);

        ResponseEntity<ApiBaseResponse<RocketRes>> result = rocketService.storeRocket(Metadata.builder().build(), req);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void storeRocket_exceptionTest() {
        RocketReq req = RocketReq.builder().name("Rockey Gerung").astronoutCap(3).fuelTank(100).build();
        when(rocketDataHelper.storeRocket(any())).thenThrow(new NullPointerException());

        Assertions.assertThrows(RocketException.class, () -> {
            rocketService.storeRocket(Metadata.builder().build(), req);
        });
    }

    @Test
    void getListRocket_successTest() {
        List<RocketDao> listRocket = List.of(RocketDao.builder().name("Rockey Gerung").build());
        Pageable pageable = PageRequest.of(0,10);
        Page<RocketDao> mockResult = new PageImpl<>(listRocket, pageable, 3);
        when(rocketDataHelper.getPageRocket(anyInt(), anyInt())).thenReturn(mockResult);

        ResponseEntity<ApiBaseResponse<PageRocketRes>> result = rocketService.getListRocket(Metadata.builder().build(), 0, 10);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void getListRocket_exceptionTest() {
        when(rocketDataHelper.getPageRocket(anyInt(), anyInt())).thenThrow(new NullPointerException());

        Assertions.assertThrows(RocketException.class, () -> {
            rocketService.getListRocket(Metadata.builder().build(), 0, 10);
        });
    }
}
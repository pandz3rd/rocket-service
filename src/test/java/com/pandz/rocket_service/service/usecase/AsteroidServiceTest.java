package com.pandz.rocket_service.service.usecase;

import com.pandz.rocket_service.exception.RocketException;
import com.pandz.rocket_service.model.api.asteroid.AsteroidReq;
import com.pandz.rocket_service.model.api.asteroid.AsteroidRes;
import com.pandz.rocket_service.model.api.asteroid.PageAsteroidRes;
import com.pandz.rocket_service.model.db.AsteroidDao;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.helper.AsteroidDataHelper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AsteroidService.class)
public class AsteroidServiceTest {
    @Autowired
    private AsteroidService asteroidService;

    @MockitoBean
    private AsteroidDataHelper asteroidDataHelper;

    @Test
    void storeAsteroid_successTest() {
        AsteroidReq req = AsteroidReq.builder().name("Rocky Gerung").build();
        AsteroidDao mockAsteroid = AsteroidDao.builder().name("Rocky Gerung").minDiameterKm(12.12).maxDiameterKm(12.12).isHazardous(true).build();
        when(asteroidDataHelper.storeAsteroid(any())).thenReturn(mockAsteroid);

        ResponseEntity<ApiBaseResponse<AsteroidRes>> result = asteroidService.storeAsteroid(Metadata.builder().build(), req);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getBody());
    }

    @Test()
    void storeAsteroid_exceptionTest() {
        AsteroidReq req = AsteroidReq.builder().name("Rocky Gerung").build();
        when(asteroidDataHelper.storeAsteroid(any())).thenThrow(new NullPointerException());

        Assertions.assertThrows(RocketException.class, () -> {
            asteroidService.storeAsteroid(Metadata.builder().build(), req);
        });
    }

    @Test
    void getListAsteroid_successTest() {
        List<AsteroidDao> listAsteroid = List.of(AsteroidDao.builder().name("Rocky Gerung").minDiameterKm(12.12).maxDiameterKm(12.12).isHazardous(true).build());
        Pageable pageable = PageRequest.of(0, 10);
        Page<AsteroidDao> mockResult = new PageImpl<>(listAsteroid, pageable, 5);
        when(asteroidDataHelper.getPageAsteroid(anyInt(), anyInt())).thenReturn(mockResult);

        ResponseEntity<ApiBaseResponse<PageAsteroidRes>> result = asteroidService.getListAsteroid(Metadata.builder().build(), 0, 10);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(1L, result.getBody().getData().getTotalSize());
    }

    @Test
    void getListAsteroid_exceptionTest() {
        when(asteroidDataHelper.getPageAsteroid(anyInt(), anyInt())).thenThrow(new NullPointerException());

        Assertions.assertThrows(RocketException.class, () -> {
            asteroidService.getListAsteroid(Metadata.builder().build(), 0, 10);
        });
    }
}
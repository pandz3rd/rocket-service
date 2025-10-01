package com.pandz.rocket_service.service.helper;

import com.pandz.rocket_service.model.db.AsteroidDao;
import com.pandz.rocket_service.repository.AsteroidRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AsteroidDataHelper.class)
public class AsteroidDataHelperTest {
    @Autowired
    private AsteroidDataHelper asteroidDataHelper;

    @MockitoBean
    private AsteroidRepository asteroidRepository;

    @Test
    void storeAsteroid_test() {
        AsteroidDao req = AsteroidDao.builder().name("Astra").build();
        when(asteroidRepository.save(any())).thenReturn(req);
        AsteroidDao result = asteroidDataHelper.storeAsteroid(req);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Astra", result.getName());
    }

    @Test
    void getPageAsteroid_test() {
        List<AsteroidDao> listAsteroid = List.of(AsteroidDao.builder().name("Bambang").build());
        Pageable pageable = PageRequest.of(0, 10, Sort.by("asc"));
        Page<AsteroidDao> mockResult = new PageImpl<>(listAsteroid, pageable, 5);
        when(asteroidRepository.findList(any())).thenReturn(mockResult);

        Page<AsteroidDao> result = asteroidDataHelper.getPageAsteroid(0, 10);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getTotalElements());
    }
}
package com.pandz.rocket_service.service.helper;

import com.pandz.rocket_service.model.db.RocketDao;
import com.pandz.rocket_service.repository.RocketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RocketDataHelper.class)
public class RocketDataHelperTest {
    @Autowired
    private RocketDataHelper rocketDataHelper;

    @MockitoBean
    private RocketRepository rocketRepository;

    @Test
    void storeRocket_test() {
        RocketDao req = RocketDao.builder().name("Owi").build();
        when(rocketRepository.save(any())).thenReturn(req);

        RocketDao result = rocketDataHelper.storeRocket(req);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Owi", result.getName());
    }

    @Test
    void getPageRocket_test() {
        List<RocketDao> listRocket = List.of(RocketDao.builder().name("Rocket Owi Besar").build());
        Pageable pageable = PageRequest.of(0, 10, Sort.by("asc"));
        Page<RocketDao> mockResult = new PageImpl<>(listRocket, pageable, 5);
        when(rocketRepository.findList(any())).thenReturn(mockResult);

        Page<RocketDao> result = rocketDataHelper.getPageRocket(0, 10);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getTotalElements());
    }
}
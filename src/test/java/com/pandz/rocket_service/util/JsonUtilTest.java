package com.pandz.rocket_service.util;

import com.pandz.rocket_service.model.api.rocket.RocketRes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonUtilTest {
    @Test
    public void objectAsStringJson_successTest() {
        String result = JsonUtil.objectAsStringJson(RocketRes.builder().build());
        assertNotNull(result);
    }

    @Test
    public void objectAsStringJson_exceptionResult() {
        String result = JsonUtil.objectAsStringJson(new Object());
        assertEquals("", result);
    }
}
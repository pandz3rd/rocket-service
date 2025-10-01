package com.pandz.rocket_service.service.helper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TriangleHelper.class)
public class TriangleHelperTest {
    @Autowired
    private TriangleHelper triangleHelper;

    @Test
    void getNormalTriangle_test() {
        String result = triangleHelper.getNormalTriangle(5);
        log.info("Result: " + result);
        Assertions.assertNotNull(result);
    }

    @Test
    void getPascalTriangle_test() {
        String result = triangleHelper.getPascalTriangle(5);
        log.info("Result: " + result);
        Assertions.assertNotNull(result);
    }
}
package com.pandz.rocket_service.service.usecase;

import com.pandz.rocket_service.exception.RocketException;
import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.helper.TriangleHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AstronoutService.class)
public class AstronoutServiceTest {
    @Autowired
    private AstronoutService astronoutService;

    @MockitoBean
    private TriangleHelper triangleHelper;

    @Test
    void displayResult_normalTest() {
        when(triangleHelper.getNormalTriangle(anyInt())).thenReturn("*");

        String result = astronoutService.displayResult(Metadata.builder().build(), "NORMAL");
        Assertions.assertEquals("*", result);
    }

    @Test
    void displayResult_PascalTest() {
        when(triangleHelper.getPascalTriangle(anyInt())).thenReturn("*");

        String result = astronoutService.displayResult(Metadata.builder().build(), "PASCAL");
        Assertions.assertEquals("*", result);
    }

    @Test
    void displayResult_exceptionTest() {
        when(triangleHelper.getNormalTriangle(anyInt())).thenThrow(new NullPointerException());

        Assertions.assertThrows(RocketException.class, () -> {
            astronoutService.displayResult(Metadata.builder().build(), "NORMAL");
        });
    }
}
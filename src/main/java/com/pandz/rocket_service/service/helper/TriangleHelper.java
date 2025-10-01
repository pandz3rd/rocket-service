package com.pandz.rocket_service.service.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TriangleHelper {

    public String getNormalTriangle(int max) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < max + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                result.append(j).append(" ");
            }
            result.append("\n");
        }
        return result.toString().trim();
    }

    public String getPascalTriangle(int max) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < max; i++) {
            int number = 1;
            for (int j = 0; j <= i; j++) {
                result.append(number).append(" ");
                number = number * (i - j) / (j + 1);
            }
            result.append("\n");
        }
        return result.toString().trim();
    }
}

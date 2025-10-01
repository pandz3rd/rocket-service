package com.pandz.rocket_service.service.usecase;

import com.pandz.rocket_service.exception.RocketException;
import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.helper.TriangleHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.pandz.rocket_service.constant.GeneralConstant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AstronoutService {
    @Autowired
    private TriangleHelper triangleHelper;

    public String displayResult(Metadata metadata, String type) {
        log.info("Start display result astronout");
        String result = "";
        try {
            if ("PASCAL".equalsIgnoreCase(type)) {
                result = triangleHelper.getPascalTriangle(5);
            } else {
                result = triangleHelper.getNormalTriangle(5);
            }
        } catch (Exception e) {
            log.error("Error when display result: " + e.getMessage());
            throw new RocketException(ERROR_MESSAGE_SYSTEM_ERROR, RC_CODE_SYSTEM_ERROR, ERROR_DESCRIPTION_SYSTEM_ERROR);
        }
        return result;
    }
}

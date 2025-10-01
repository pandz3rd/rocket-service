package com.pandz.rocket_service.util;

import com.pandz.rocket_service.constant.GeneralConstant;
import com.pandz.rocket_service.model.api.rocket.RocketRes;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseUtilTest {
    @Test
    void buildResponse1_test() {
        var result = ResponseUtil.buildResponse("00", "Request Error", RocketRes.builder().build());
        assertNotNull(result);
    }

    @Test
    void buildResponse2_test() {
        var result = ResponseUtil.buildResponse("99", "sys_err");
        assertNotNull(result);
    }

    @Test
    void buildHttpResponse_test() {
        ResponseEntity<ApiBaseResponse<Serializable>> result = ResponseUtil.buildHttpResponse(ApiBaseResponse.builder().responseCode(GeneralConstant.RC_CODE_SUCCESS).build());
        ResponseEntity<ApiBaseResponse<Serializable>> result2 = ResponseUtil.buildHttpResponse(ApiBaseResponse.builder().responseCode(GeneralConstant.RC_CODE_DECLINE).build());

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, result2.getStatusCode());
    }
}
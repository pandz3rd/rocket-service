package com.pandz.rocket_service.exception;

import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.util.ResponseUtil;

import java.io.Serializable;

public class RocketException extends RuntimeException {
    private final String messageCode;
    private final String errorCode;
    private final String errorMessage;

    public RocketException(String message, String code, String errorMessage) {
        super(message);
        this.messageCode = message;
        this.errorCode = code;
        this.errorMessage = errorMessage;
    }

    public ApiBaseResponse<Serializable> apiResponseBase() {
        return ResponseUtil.buildResponse(this.messageCode, this.errorCode);
    }
}

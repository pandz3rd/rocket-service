package com.pandz.rocket_service.exception;

import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;

import static com.pandz.rocket_service.constant.GeneralConstant.*;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleSecurityException(Exception exception) {
        ApiBaseResponse<Serializable> result = new ApiBaseResponse<>();
        result.setResponseCode(RC_CODE_SYSTEM_ERROR);
        result.setResponseMessage(ERROR_DESCRIPTION_SYSTEM_ERROR);
        result.setTraceId(MDC.get(TRACE_ID));
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RocketException.class})
    public ResponseEntity<Object> rocketExceptionHandler(RocketException exception) {
        ApiBaseResponse<Serializable> responseException = exception.apiResponseBase();

        HttpStatus httpStatus = HttpStatus.OK;

        if (RC_CODE_SYSTEM_ERROR.equalsIgnoreCase(responseException.getResponseCode())) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (RC_CODE_DECLINE.equalsIgnoreCase(responseException.getResponseCode())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(responseException, httpStatus);
    }
}

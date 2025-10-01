package com.pandz.rocket_service.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiBaseResponse <T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -8060959230887663741L;
    @JsonProperty("response_code")
    private String responseCode;
    @JsonProperty("response_message")
    private String responseMessage;
    private String traceId;

    private T data;
}

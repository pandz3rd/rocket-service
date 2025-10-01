package com.pandz.rocket_service.model.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Metadata implements Serializable {
    private static final long serialVersionUID = -4116921471891634292L;
    private String ipAddress;
    private String userAgent;
    private String channel;
    private String appVersion;
    private String signature;
    private String timestamp;
    private String requestId;
    private String bearerToken;
}

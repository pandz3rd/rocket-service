package com.pandz.rocket_service.model.api.astronout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AstronoutRes implements Serializable {
    private static final long serialVersionUID = 311690368590658270L;
    private String result;
}

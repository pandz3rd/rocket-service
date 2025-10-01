package com.pandz.rocket_service.model.api.asteroid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsteroidReq {
    private String name;
    private double minDiameterKm;
    private double maxDiameterKm;
    private boolean isHazardous;
}

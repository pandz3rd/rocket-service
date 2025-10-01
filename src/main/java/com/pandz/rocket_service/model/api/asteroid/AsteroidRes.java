package com.pandz.rocket_service.model.api.asteroid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsteroidRes implements Serializable {
    private static final long serialVersionUID = 726217904044648520L;
    private String asteroidName;
    private double minDiameterKm;
    private double maxDiameterKm;
    private boolean isHazardous;
}

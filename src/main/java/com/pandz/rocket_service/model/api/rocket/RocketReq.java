package com.pandz.rocket_service.model.api.rocket;

import com.pandz.rocket_service.model.api.asteroid.AsteroidReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RocketReq {

    private String name;
    private int fuelTank;
    private int astronoutCap;
    private List<AsteroidReq> listAsteroid;
}

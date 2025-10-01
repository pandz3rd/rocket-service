package com.pandz.rocket_service.model.api.rocket;

import com.pandz.rocket_service.model.db.AsteroidDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RocketRes implements Serializable {
    private static final long serialVersionUID = -9092005685412629514L;
    private String rocketName;
    private int rocketFuelTank;
    private int astronoutCap;
    private List<AsteroidDao> listAsteroid;
}

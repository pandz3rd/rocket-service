package com.pandz.rocket_service.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "asteroids", schema = "spaces")
public class AsteroidDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "min_diameter_km")
    private Double minDiameterKm;

    @Column(name = "max_diameter_km")
    private Double maxDiameterKm;

    @Column(name = "is_hazardous")
    private boolean isHazardous;

    @JsonIgnore // ignore serialization this field when get data
    @ManyToMany(mappedBy = "asteroids")
    private List<RocketDao> rockets = new ArrayList<>();
}

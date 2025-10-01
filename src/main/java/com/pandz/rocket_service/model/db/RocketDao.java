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
@Table(name = "rockets", schema = "spaces")
public class RocketDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "fuel_tank", nullable = false)
    private int fuelTank;

    @Column(name = "astronout_cap", nullable = false)
    private int astronoutCap;

    @JsonIgnore // ignore serialization this field when get data
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "rocket_asteroid", // join table
        joinColumns = @JoinColumn(name = "rocket_id"), // FK to rocket
        inverseJoinColumns = @JoinColumn(name = "asteroid_id") // FK to asteroid
    )
    private List<AsteroidDao> asteroids = new ArrayList<>();
}

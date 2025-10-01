package com.pandz.rocket_service.repository;

import com.pandz.rocket_service.model.db.AsteroidDao;
import com.pandz.rocket_service.model.db.RocketDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AsteroidRepository extends JpaRepository<AsteroidDao, Long> {
    @Query(value = "SELECT * FROM spaces.`asteroids`",
            countQuery = "SELECT COUNT(*) FROM spaces.`asteroids`", nativeQuery = true)
    Page<AsteroidDao> findList(Pageable pageable);
}

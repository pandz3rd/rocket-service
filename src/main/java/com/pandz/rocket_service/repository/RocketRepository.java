package com.pandz.rocket_service.repository;

import com.pandz.rocket_service.model.db.RocketDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RocketRepository extends JpaRepository<RocketDao, Long> {
    @Query(value = "SELECT * FROM spaces.`rockets`",
        countQuery = "SELECT COUNT(*) FROM spaces.`rockets`", nativeQuery = true)
    Page<RocketDao> findList(Pageable pageable);
}

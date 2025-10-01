package com.pandz.rocket_service.service.helper;

import com.pandz.rocket_service.model.db.AsteroidDao;
import com.pandz.rocket_service.repository.AsteroidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsteroidDataHelper {
    @Autowired
    private AsteroidRepository asteroidRepository;

    public AsteroidDao storeAsteroid(AsteroidDao asteroidDao) {
        return asteroidRepository.save(asteroidDao);
    }

    public Page<AsteroidDao> getPageAsteroid(int page, int size) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return asteroidRepository.findList(pageable);
    }
}

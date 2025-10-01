package com.pandz.rocket_service.service.helper;

import com.pandz.rocket_service.model.db.RocketDao;
import com.pandz.rocket_service.repository.RocketRepository;
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
public class RocketDataHelper {
    @Autowired
    private RocketRepository rocketRepository;

    public RocketDao storeRocket(RocketDao rocketDao) {
        return rocketRepository.save(rocketDao);
    }

    public Page<RocketDao> getPageRocket(int page, int size) {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return rocketRepository.findList(pageable);
    }
}

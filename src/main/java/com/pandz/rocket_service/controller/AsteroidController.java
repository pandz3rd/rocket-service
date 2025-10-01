package com.pandz.rocket_service.controller;

import com.pandz.rocket_service.model.api.asteroid.AsteroidReq;
import com.pandz.rocket_service.model.api.asteroid.AsteroidRes;
import com.pandz.rocket_service.model.api.asteroid.PageAsteroidRes;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.usecase.AsteroidService;
import com.pandz.rocket_service.util.MetadataUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/asteroid")
public class AsteroidController {
    @Autowired
    private AsteroidService asteroidService;

    @PostMapping("store")
    public ResponseEntity<ApiBaseResponse<AsteroidRes>> storeAsteroid(@RequestBody AsteroidReq req, HttpServletRequest servletRequest) {
        Metadata metadata = MetadataUtil.constructMetadata(servletRequest);
        return asteroidService.storeAsteroid(metadata, req);
    }

    @GetMapping("list")
    public ResponseEntity<ApiBaseResponse<PageAsteroidRes>> getListAsteroid(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest servletRequest) {
        Metadata metadata = MetadataUtil.constructMetadata(servletRequest);
        return asteroidService.getListAsteroid(metadata, page, size);
    }
}

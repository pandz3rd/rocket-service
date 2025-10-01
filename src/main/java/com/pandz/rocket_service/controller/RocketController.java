package com.pandz.rocket_service.controller;

import com.pandz.rocket_service.model.api.rocket.PageRocketRes;
import com.pandz.rocket_service.model.api.rocket.RocketReq;
import com.pandz.rocket_service.model.api.rocket.RocketRes;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.usecase.RocketService;
import com.pandz.rocket_service.util.MetadataUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rocket")
public class RocketController {
    @Autowired
    private RocketService rocketService;

    @PostMapping("store")
    public ResponseEntity<ApiBaseResponse<RocketRes>> storeRocket(@RequestBody RocketReq req, HttpServletRequest servletRequest) {
        Metadata metadata = MetadataUtil.constructMetadata(servletRequest);
        return rocketService.storeRocket(metadata, req);
    }

    @GetMapping("list")
    public ResponseEntity<ApiBaseResponse<PageRocketRes>> getListRocket(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest servletRequest) {
        Metadata metadata = MetadataUtil.constructMetadata(servletRequest);
        return rocketService.getListRocket(metadata, page, size);
    }
}

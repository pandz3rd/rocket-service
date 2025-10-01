package com.pandz.rocket_service.controller;

import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.usecase.AstronoutService;
import com.pandz.rocket_service.util.MetadataUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/astronout")
public class AstronoutController {
    @Autowired
    private AstronoutService astronoutService;

    @GetMapping("display")
    public String displayResult(
            @RequestParam String type,
            HttpServletRequest servletRequest) {
        Metadata metadata = MetadataUtil.constructMetadata(servletRequest);
        return astronoutService.displayResult(metadata, type);
    }
}

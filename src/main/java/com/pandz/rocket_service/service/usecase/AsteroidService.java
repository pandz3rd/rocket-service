package com.pandz.rocket_service.service.usecase;

import com.pandz.rocket_service.exception.RocketException;
import com.pandz.rocket_service.model.api.asteroid.AsteroidReq;
import com.pandz.rocket_service.model.api.asteroid.AsteroidRes;
import com.pandz.rocket_service.model.api.asteroid.PageAsteroidRes;
import com.pandz.rocket_service.model.db.AsteroidDao;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.helper.AsteroidDataHelper;
import com.pandz.rocket_service.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pandz.rocket_service.constant.GeneralConstant.*;

@Slf4j
@Service
public class AsteroidService {
    @Autowired
    private AsteroidDataHelper asteroidDataHelper;

    public ResponseEntity<ApiBaseResponse<AsteroidRes>> storeAsteroid(Metadata metadata, AsteroidReq req) {
        log.info("Start store asteroid");
        AsteroidRes response = null;
        try {
            AsteroidDao asteroidDao = AsteroidDao.builder()
                .name(req.getName())
                .minDiameterKm(req.getMinDiameterKm())
                .maxDiameterKm(req.getMaxDiameterKm())
                .isHazardous(req.isHazardous())
                .build();

            AsteroidDao asteroidResult = asteroidDataHelper.storeAsteroid(asteroidDao);
            response = AsteroidRes.builder()
                .asteroidName(asteroidResult.getName())
                .minDiameterKm(asteroidResult.getMinDiameterKm())
                .maxDiameterKm(asteroidResult.getMaxDiameterKm())
                .isHazardous(asteroidResult.isHazardous())
                .build();
        } catch (Exception e) {
            log.error("Error when store asteroid: " + e.getMessage());
            throw new RocketException(ERROR_MESSAGE_SYSTEM_ERROR, RC_CODE_SYSTEM_ERROR, ERROR_DESCRIPTION_SYSTEM_ERROR);
        }
        return ResponseUtil.buildHttpResponse(ResponseUtil.buildResponse(RC_CODE_SUCCESS, ERROR_MESSAGE_SUCCESS, response));
    }

    public ResponseEntity<ApiBaseResponse<PageAsteroidRes>> getListAsteroid(Metadata metadata, int page, int size) {
        log.info("Start get list asteroid");
        PageAsteroidRes response = null;
        try {
            Page<AsteroidDao> pageAsteroid = asteroidDataHelper.getPageAsteroid(page, size);
            List<AsteroidRes> listAsteroidRes = pageAsteroid.getContent().stream().map(ast -> AsteroidRes.builder()
                .asteroidName(ast.getName())
                .minDiameterKm(ast.getMinDiameterKm())
                .maxDiameterKm(ast.getMaxDiameterKm())
                .isHazardous(ast.isHazardous())
                .build()).toList();

            response = PageAsteroidRes.builder()
                .list(listAsteroidRes)
                .totalPage(pageAsteroid.getTotalPages())
                .totalSize((int) pageAsteroid.getTotalElements())
                .build();
        } catch (Exception e) {
            log.error("Error when get list asteroid: " + e.getMessage());
            throw new RocketException(ERROR_MESSAGE_SYSTEM_ERROR, RC_CODE_SYSTEM_ERROR, ERROR_DESCRIPTION_SYSTEM_ERROR);
        }
        return ResponseUtil.buildHttpResponse(ResponseUtil.buildResponse(RC_CODE_SUCCESS, ERROR_MESSAGE_SUCCESS, response));
    }
}

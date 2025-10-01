package com.pandz.rocket_service.service.usecase;

import com.pandz.rocket_service.exception.RocketException;
import com.pandz.rocket_service.model.api.rocket.PageRocketRes;
import com.pandz.rocket_service.model.api.rocket.RocketReq;
import com.pandz.rocket_service.model.api.rocket.RocketRes;
import com.pandz.rocket_service.model.db.AsteroidDao;
import com.pandz.rocket_service.model.db.RocketDao;
import com.pandz.rocket_service.model.internal.ApiBaseResponse;
import com.pandz.rocket_service.model.internal.Metadata;
import com.pandz.rocket_service.service.helper.RocketDataHelper;
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
public class RocketService {
    @Autowired
    private RocketDataHelper rocketDataHelper;

    public ResponseEntity<ApiBaseResponse<RocketRes>> storeRocket(Metadata metadata, RocketReq req) {
        log.info("Start store rocket");
        RocketRes response = null;
        try {
            List<AsteroidDao> listAsteroid = req.getListAsteroid().stream().map(ast -> AsteroidDao.builder()
                .name(ast.getName())
                .maxDiameterKm(ast.getMaxDiameterKm())
                .minDiameterKm(ast.getMinDiameterKm())
                .isHazardous(ast.isHazardous())
                .build()).toList();

            RocketDao rocketDao = RocketDao.builder()
                .name(req.getName())
                .fuelTank(req.getFuelTank())
                .astronoutCap(req.getAstronoutCap())
                .build();
            rocketDao.setAsteroids(listAsteroid);

            RocketDao resultRocket = rocketDataHelper.storeRocket(rocketDao);
            response = RocketRes.builder()
                .rocketName(resultRocket.getName())
                .rocketFuelTank(resultRocket.getFuelTank())
                .astronoutCap(resultRocket.getAstronoutCap())
                .listAsteroid(resultRocket.getAsteroids())
                .build();

        } catch (Exception e) {
            log.error("Error when store rocket: " + e.getMessage());
            throw new RocketException(ERROR_MESSAGE_SYSTEM_ERROR, RC_CODE_SYSTEM_ERROR, ERROR_DESCRIPTION_SYSTEM_ERROR);
        }
        return ResponseUtil.buildHttpResponse(ResponseUtil.buildResponse(RC_CODE_SUCCESS, ERROR_MESSAGE_SUCCESS, response));
    }

    public ResponseEntity<ApiBaseResponse<PageRocketRes>> getListRocket(Metadata metadata, int page, int size) {
        log.info("Start get list rocket");
        PageRocketRes response = null;
        try {
            Page<RocketDao> pageRocket = rocketDataHelper.getPageRocket(page, size);
            List<RocketRes> listRocketRes = pageRocket.getContent().stream().map(rock -> RocketRes.builder()
                .rocketName(rock.getName())
                .rocketFuelTank(rock.getFuelTank())
                .astronoutCap(rock.getAstronoutCap())
                .listAsteroid(rock.getAsteroids())
                .build()).toList();

            response = PageRocketRes.builder()
                .list(listRocketRes)
                .totalPage(pageRocket.getTotalPages())
                .totalSize((int) pageRocket.getTotalElements())
                .build();
        } catch (Exception e) {
            log.error("Error when get list rocket: " + e.getMessage());
            throw new RocketException(ERROR_MESSAGE_SYSTEM_ERROR, RC_CODE_SYSTEM_ERROR, ERROR_DESCRIPTION_SYSTEM_ERROR);
        }
        return ResponseUtil.buildHttpResponse(ResponseUtil.buildResponse(RC_CODE_SUCCESS, ERROR_MESSAGE_SUCCESS, response));
    }
}

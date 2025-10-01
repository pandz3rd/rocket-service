package com.pandz.rocket_service.model.api.rocket;

import com.pandz.rocket_service.model.api.rocket.RocketRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRocketRes implements Serializable {
    private static final long serialVersionUID = -9187132093202453943L;
    private List<RocketRes> list;
    private int totalPage;
    private int totalSize;
}

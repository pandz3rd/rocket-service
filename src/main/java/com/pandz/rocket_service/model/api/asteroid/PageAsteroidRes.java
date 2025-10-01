package com.pandz.rocket_service.model.api.asteroid;

import com.pandz.rocket_service.model.api.asteroid.AsteroidRes;
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
public class PageAsteroidRes implements Serializable {
    private static final long serialVersionUID = 4517668868366878466L;
    private List<AsteroidRes> list;
    private int totalPage;
    private int totalSize;
}

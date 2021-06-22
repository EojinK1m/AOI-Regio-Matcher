package com.eojin.aoi_region_matcher.dto.response;


import com.eojin.aoi_region_matcher.dto.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AoiResponse {
    private Integer id;

    private List<Coordinate> area;

    private String name;
}

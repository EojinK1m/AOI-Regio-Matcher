package com.eojin.aoi_region_matcher.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
public class GetIntersectedAoiResponse {
    List<AoiResponse> aois;
}

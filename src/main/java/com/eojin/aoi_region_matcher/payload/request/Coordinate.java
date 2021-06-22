package com.eojin.aoi_region_matcher.payload.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
@Builder
public class Coordinate {

    @NotNull
    private Double x;

    @NotNull
    private Double y;
}
package com.eojin.aoi_region_matcher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
@Builder
@AllArgsConstructor
public class Coordinate {

    @NotNull
    private Double x;

    @NotNull
    private Double y;
}

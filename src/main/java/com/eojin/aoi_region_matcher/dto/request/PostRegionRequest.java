package com.eojin.aoi_region_matcher.dto.request;

import com.eojin.aoi_region_matcher.dto.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostRegionRequest {
    private String name;

    @Size(min = 4)
    @NotEmpty
    private List<Coordinate> area;
}

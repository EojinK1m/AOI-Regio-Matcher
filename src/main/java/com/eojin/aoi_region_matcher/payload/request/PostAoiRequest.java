package com.eojin.aoi_region_matcher.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostAoiRequest {
    private String name;

    @Size(min = 4)
    @NotEmpty
    private List<Coordinate> area;
}

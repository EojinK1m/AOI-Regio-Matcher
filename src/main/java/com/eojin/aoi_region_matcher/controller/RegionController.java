package com.eojin.aoi_region_matcher.controller;

import com.eojin.aoi_region_matcher.dto.request.PostRegionRequest;
import com.eojin.aoi_region_matcher.dto.response.PostRegionResponse;
import com.eojin.aoi_region_matcher.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostRegionResponse postRegion(@RequestBody @Valid PostRegionRequest request){
        return regionService.createRegion(request);
    }

}

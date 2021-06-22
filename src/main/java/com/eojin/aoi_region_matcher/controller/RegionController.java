package com.eojin.aoi_region_matcher.controller;

import com.eojin.aoi_region_matcher.payload.request.PostRegionRequest;
import com.eojin.aoi_region_matcher.payload.request.PostRegionResponse;
import com.eojin.aoi_region_matcher.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping
    public PostRegionResponse postRegion(@RequestBody @Valid PostRegionRequest request){
        return regionService.create_region(request);
    }

}

package com.eojin.aoi_region_matcher.controller;


import com.eojin.aoi_region_matcher.dto.response.GetIntersectedAoiResponse;
import com.eojin.aoi_region_matcher.service.AoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regions/{regionId}/aois/intersects")
@RequiredArgsConstructor
public class IntersectedAoiController {
    private final AoiService aoiService;

    @GetMapping
    public GetIntersectedAoiResponse getIntersectedAois(@PathVariable Integer regionId){
        return aoiService.getAoiIntersectedWithRegion(regionId);
    }
}

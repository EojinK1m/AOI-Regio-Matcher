package com.eojin.aoi_region_matcher.controller;

import com.eojin.aoi_region_matcher.dto.request.PostAoiRequest;
import com.eojin.aoi_region_matcher.dto.response.PostAoiResponse;
import com.eojin.aoi_region_matcher.service.AoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/aois")
@RequiredArgsConstructor
public class AoiController {

    private final AoiService aoiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostAoiResponse postRegion(@RequestBody @Valid PostAoiRequest request){
        System.out.print(request.getName().getClass());
        return aoiService.createAoi(request);
    }

}

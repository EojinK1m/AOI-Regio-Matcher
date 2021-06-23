package com.eojin.aoi_region_matcher.controller;

import com.eojin.aoi_region_matcher.dto.request.PostAoiRequest;
import com.eojin.aoi_region_matcher.dto.response.AoiResponse;
import com.eojin.aoi_region_matcher.dto.response.PostAoiResponse;
import com.eojin.aoi_region_matcher.exception.BadRequestException;
import com.eojin.aoi_region_matcher.service.AoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/aois")
@RequiredArgsConstructor
public class AoiController {

    private final AoiService aoiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostAoiResponse postAoi(@RequestBody @Valid PostAoiRequest request) {
        return aoiService.createAoi(request);
    }

    @GetMapping
    public AoiResponse getNearestAoi(
            @RequestParam(name = "lat") Optional<String> xParameter,
            @RequestParam(name = "long") Optional<String> yParameter)
    {
        Double x, y;

        try{
            x = Double.valueOf(xParameter.get());
            y = Double.valueOf(yParameter.get());
        }catch (Exception e){
            throw new BadRequestException();
        }

        return aoiService.getNearestAoi(x, y);
    }

}

package com.eojin.aoi_region_matcher.service;

import com.eojin.aoi_region_matcher.model.AOI;
import com.eojin.aoi_region_matcher.payload.response.AoiResponse;
import com.eojin.aoi_region_matcher.payload.request.Coordinate;
import com.eojin.aoi_region_matcher.payload.response.GetIntersectedAoiResponse;
import com.eojin.aoi_region_matcher.repository.AoiRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AoiService {
    private final AoiRepository aoiRepository;

    public GetIntersectedAoiResponse getAoiIntersectedWithRegion(Integer id){
        List<AoiResponse> aoiResponses = new ArrayList<>();

        List<AOI> aois = aoiRepository.findOverlappedAoiByRegionId(id);
        System.out.print(aois);

        for(AOI aoi: aois){
            aoiResponses.add(
                    new AoiResponse(
                            aoi.getId(),
                            convertPolygonToCoordinateList(aoi.getArea()),
                            aoi.getName()
                    )
            );
        }

        return new GetIntersectedAoiResponse(aoiResponses);
    }

    private List<Coordinate> convertPolygonToCoordinateList(Polygon polygon){
        List<Coordinate> coordinates = new ArrayList<>();

        for(org.locationtech.jts.geom.Coordinate coordinate: polygon.getCoordinates()){
            coordinates.add(
                    Coordinate.builder().x(coordinate.getX()).y(coordinate.getY()).build()
            );
        }

        return coordinates;
    }



}

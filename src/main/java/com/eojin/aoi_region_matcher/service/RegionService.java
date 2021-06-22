package com.eojin.aoi_region_matcher.service;


import com.eojin.aoi_region_matcher.model.Region;
import com.eojin.aoi_region_matcher.payload.request.Coordinate;
import com.eojin.aoi_region_matcher.payload.request.PostRegionRequest;
import com.eojin.aoi_region_matcher.payload.request.PostRegionResponse;
import com.eojin.aoi_region_matcher.repository.region.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public PostRegionResponse create_region(PostRegionRequest request){
        Polygon polygon = convert_area_into_polygon(request.getArea());

        Region region = Region
                .builder()
                .name(request.getName())
                .area(polygon)
                .build();
        regionRepository.save(region);

        return new PostRegionResponse(region.getId());
    }

    private Polygon convert_area_into_polygon(List<Coordinate> area){
        GeometryFactory fact = new GeometryFactory(new PrecisionModel(), 4326);
        org.locationtech.jts.geom.Coordinate[] coordinates =
                new org.locationtech.jts.geom.Coordinate[area.size()];

        for(int i=0; i<area.size(); i++){
            Coordinate coordinate = area.get(i);
            coordinates[i] = new org.locationtech.jts.geom.Coordinate(coordinate.getX(), coordinate.getY());
        }

        return fact.createPolygon(coordinates);
    }

}

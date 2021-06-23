package com.eojin.aoi_region_matcher.service;


import com.eojin.aoi_region_matcher.exception.RegionDuplicationException;
import com.eojin.aoi_region_matcher.model.Region;
import com.eojin.aoi_region_matcher.dto.request.PostRegionRequest;
import com.eojin.aoi_region_matcher.dto.response.PostRegionResponse;
import com.eojin.aoi_region_matcher.repository.RegionRepository;
import com.eojin.aoi_region_matcher.util.GeometryConverter;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final GeometryConverter geometryConverter;

    public PostRegionResponse createRegion(PostRegionRequest request){
        throwIfSameNameRegionExist(request.getName());
        Polygon polygon = geometryConverter.convertCoordinatesToPolygon(request.getArea());

        Region region = Region
                .builder()
                .name(request.getName())
                .area(polygon)
                .build();
        region = regionRepository.save(region);

        return new PostRegionResponse(region.getId());
    }

    private void throwIfSameNameRegionExist(String name){
        if(regionRepository.getRegionByName(name) != null){
            throw new RegionDuplicationException();
        }
    }
}

package com.eojin.aoi_region_matcher.service;

import com.eojin.aoi_region_matcher.exception.AoiDuplicationException;
import com.eojin.aoi_region_matcher.exception.NotFountException;
import com.eojin.aoi_region_matcher.model.AOI;
import com.eojin.aoi_region_matcher.dto.request.PostAoiRequest;
import com.eojin.aoi_region_matcher.dto.response.AoiResponse;
import com.eojin.aoi_region_matcher.dto.response.GetIntersectedAoiResponse;
import com.eojin.aoi_region_matcher.dto.response.PostAoiResponse;
import com.eojin.aoi_region_matcher.repository.AoiRepository;
import com.eojin.aoi_region_matcher.repository.RegionRepository;
import com.eojin.aoi_region_matcher.util.GeometryConverter;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AoiService {
    private final AoiRepository aoiRepository;

    private final RegionRepository regionRepository;

    private final GeometryConverter geometryConverter;

    public GetIntersectedAoiResponse getAoiIntersectedWithRegion(Integer id){
        throwIfNotRegionExist(id);

        List<AoiResponse> aoiResponses = new ArrayList<>();
        List<AOI> aois = aoiRepository.findOverlappedAoiByRegionId(id);

        for(AOI aoi: aois){
            aoiResponses.add(
                    new AoiResponse(
                            aoi.getId(),
                            geometryConverter.convertPolygonToCoordinateList(aoi.getArea()),
                            aoi.getName()
                    )
            );
        }

        return new GetIntersectedAoiResponse(aoiResponses);
    }

    private void throwIfNotRegionExist(Integer id){
        if(regionRepository.getRegionById(id)==null){
            throw new NotFountException();
        }
    }


    public PostAoiResponse createAoi(PostAoiRequest request){
        throwIfSameNameAoiExist(request.getName());
        Polygon polygon = geometryConverter.convertCoordinatesToPolygon(request.getArea());

        AOI aoi = AOI
                .builder()
                .name(request.getName())
                .area(polygon)
                .build();
        aoi = aoiRepository.save(aoi);

        return new PostAoiResponse(aoi.getId());
    }

    private void throwIfSameNameAoiExist(String name){
        if(aoiRepository.getByName(name) != null){
            throw new AoiDuplicationException();
        }
    }

    public AoiResponse getNearestAoi(Double x, Double y){
        AOI aoi = aoiRepository.getNearestAoiByPoint(x, y);

        if(aoi == null){
            throw new NotFountException();
        }

        return new AoiResponse(
                aoi.getId(),
                geometryConverter.convertPolygonToCoordinateList(aoi.getArea()),
                aoi.getName()
        );
    }

}

package com.eojin.aoi_region_matcher;


import com.eojin.aoi_region_matcher.model.Region;
import com.eojin.aoi_region_matcher.dto.request.PostRegionRequest;
import com.eojin.aoi_region_matcher.repository.RegionRepository;
import com.eojin.aoi_region_matcher.service.RegionService;
import com.eojin.aoi_region_matcher.util.GeometryConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {
    @InjectMocks
    RegionService regionService;

    @Mock
    RegionRepository regionRepository;

    @Mock
    GeometryConverter geometryConverter;


    @Test
    public void When_CreateRegionSuccess_Then_ReturnResponseHavingRegionId(){
        Region expectRegion = Region.builder().id(3).name("한라산").area(null).build();
        PostRegionRequest request = new PostRegionRequest("한라산", new ArrayList<>());

        Mockito.when(geometryConverter.convertCoordinatesToPolygon(request.getArea())).thenReturn(null);
        Mockito.when(regionRepository.save(any())).thenReturn(expectRegion);

        Assertions.assertEquals(expectRegion.getId(), regionService.createRegion(request).getId());
    }

    @Test
    public void When_CreateDuplicatedData_Then_ThrowDuplicatedException(){
    }



}

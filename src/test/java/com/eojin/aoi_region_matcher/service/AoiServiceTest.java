package com.eojin.aoi_region_matcher.service;


import com.eojin.aoi_region_matcher.dto.request.PostAoiRequest;
import com.eojin.aoi_region_matcher.exception.AoiDuplicationException;
import com.eojin.aoi_region_matcher.exception.NotFountException;
import com.eojin.aoi_region_matcher.model.AOI;
import com.eojin.aoi_region_matcher.repository.AoiRepository;
import com.eojin.aoi_region_matcher.repository.RegionRepository;
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
public class AoiServiceTest {
    @InjectMocks
    AoiService aoiService;

    @Mock
    AoiRepository aoiRepository;

    @Mock
    RegionRepository regionRepository;

    @Mock
    GeometryConverter geometryConverter;



    @Test
    public void When_CreateAoiSuccess_Then_ReturnResponseHavingAoiId(){
        AOI expectAoi = AOI.builder().id(3).name("한라산").area(null).build();
        PostAoiRequest request = new PostAoiRequest("한라산", new ArrayList<>());

        Mockito.when(geometryConverter.convertCoordinatesToPolygon(request.getArea())).thenReturn(null);
        Mockito.when(aoiRepository.save(any())).thenReturn(expectAoi);

        Assertions.assertEquals(expectAoi.getId(), aoiService.createAoi(request).getId());
    }

    @Test
    public void When_GiveNotExistRegionId_Then_ThrowNotFountException(){
        Mockito.when(regionRepository.getRegionById(1)).thenReturn(null);

        Assertions.assertThrows(
                NotFountException.class,
                () -> {aoiService.getAoiIntersectedWithRegion(1);}
        );
    }

    @Test
    public void When_CreateAoiAlreadySameNameExists_Then_ThrowAoiDuplicationException(){
        PostAoiRequest request = new PostAoiRequest("한라산", new ArrayList<>());

        Mockito.when(aoiRepository.getByName(request.getName()))
                .thenReturn(new AOI());

        Assertions.assertThrows(
                AoiDuplicationException.class,
                () -> {aoiService.createAoi(request);}
        );
    }

    @Test
    public void When_GetNearestAoiSuccess_Then_ReturnAoiResponse(){
        Mockito.when(aoiRepository.getNearestAoiByPoint(any(), any()))
                .thenReturn(
                        AOI.builder()
                                .id(1)
                                .area(null)
                                .name("무등산")
                                .build()
                );

        Assertions.assertEquals(1, aoiService.getNearestAoi(1.1, 1.1).getId());
    }


    @Test
    public void When_GetNearestAoiRepositoryReturnNull_Then_ThrowNotFoundException(){
        Mockito.when(aoiRepository.getNearestAoiByPoint(any(), any())).thenReturn(null);

        Assertions.assertThrows(
                NotFountException.class,
                () -> {aoiService.getNearestAoi(1.1, 2.2);}
        );
    }


}

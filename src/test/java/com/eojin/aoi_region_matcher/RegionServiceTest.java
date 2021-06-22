package com.eojin.aoi_region_matcher;


import com.eojin.aoi_region_matcher.model.Region;
import com.eojin.aoi_region_matcher.dto.request.PostRegionRequest;
import com.eojin.aoi_region_matcher.repository.RegionRepository;
import com.eojin.aoi_region_matcher.service.RegionService;
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


    @Test
    public void When_CreateRegionSuccess_Then_ReturnResponseHavingRegionId(){
        Region expectRegion = Region.builder().id(3).name("ff").area(null).build();
        PostRegionRequest request = new PostRegionRequest("name", new ArrayList<>());

        Mockito.when(regionRepository.save(any())).thenReturn(expectRegion);

        Assertions.assertEquals(expectRegion.getId(), regionService.createRegion(request).getId());
    }

    @Test
    public void When_CreateDuplicatedData_Then_ThrowDuplicatedException(){

    }



}

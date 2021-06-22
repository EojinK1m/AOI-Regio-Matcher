package com.eojin.aoi_region_matcher;


import com.eojin.aoi_region_matcher.exception.NotFountException;
import com.eojin.aoi_region_matcher.repository.AoiRepository;
import com.eojin.aoi_region_matcher.repository.RegionRepository;
import com.eojin.aoi_region_matcher.service.AoiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AoiServiceTest {
    @InjectMocks
    AoiService aoiService;

    @Mock
    AoiRepository aoiRepository;

    @Mock
    RegionRepository regionRepository;

    @Test
    public void When_GiveNotExistRegionId_Then_ThrowNotFountException(){
        Mockito.when(regionRepository.getRegionById(1)).thenReturn(null);

        Assertions.assertThrows(
                NotFountException.class,
                () -> {aoiService.getAoiIntersectedWithRegion(1);}
        );
    }

}

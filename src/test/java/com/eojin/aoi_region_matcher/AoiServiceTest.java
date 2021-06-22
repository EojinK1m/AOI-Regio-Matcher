package com.eojin.aoi_region_matcher;


import com.eojin.aoi_region_matcher.repository.AoiRepository;
import com.eojin.aoi_region_matcher.service.AoiService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AoiServiceTest {
    @InjectMocks
    AoiService aoiService;

    @Mock
    AoiRepository aoiRepository;

    @Test
    public void When_GiveNotExistRegionId_Expect_ThrowNotFountException(){}

}

package com.eojin.aoi_region_matcher.controller;

import com.eojin.aoi_region_matcher.dto.response.GetIntersectedAoiResponse;
import com.eojin.aoi_region_matcher.service.AoiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;



@WebMvcTest(IntersectedAoiController.class)
public class IntersectedAoiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AoiService aoiService;

    @Test
    public void When_GetIntersectedAoiSuccess_Then_Response200AndAois() throws Exception{
        GetIntersectedAoiResponse response = new GetIntersectedAoiResponse(new ArrayList<>());

        Mockito.when(aoiService.getAoiIntersectedWithRegion(1)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/regions/1/aois/intersects"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{aois:[]}"));
    }

    @Test
    public void When_GetIntersectedAoiWithNonIntegerRegionId_Then_Response400() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/regions/23.0/aois/intersects"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

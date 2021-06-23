package com.eojin.aoi_region_matcher.controller;

import com.eojin.aoi_region_matcher.controller.RegionController;
import com.eojin.aoi_region_matcher.dto.response.PostRegionResponse;
import com.eojin.aoi_region_matcher.service.RegionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RegionService regionService;


    @Test
    public void When_PostRegionSuccess_Then_ReturnResponse_And_Response201() throws Exception {
        String request = "{\n" +
                "    \"name\":\"테스트\",\n" +
                "    \"area\":[\n" +
                "        {\n" +
                "            \"x\":126.835,\n" +
                "            \"y\":37.688\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":127.155,\n" +
                "            \"y\":37.702\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":127.184,\n" +
                "            \"y\": 37.474\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":126.835,\n" +
                "            \"y\":37.688\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        PostRegionResponse response = new PostRegionResponse(3);

        Mockito.when(regionService.createRegion(any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/regions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{id:3}"));

    }

    @Test
    public void When_PostRegionWithoutArea_Then_Response400() throws Exception {
        String request = "{\n" +
                "    \"name\":\"테스트Region\",\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/regions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void When_PostRegionWithoutName_Then_Response400() throws Exception {
        String request = "{\n" +
                "    \"area\":[\n" +
                "        {\n" +
                "            \"x\":126.835,\n" +
                "            \"y\":37.688\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":127.155,\n" +
                "            \"y\":37.702\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":127.184,\n" +
                "            \"y\": 37.474\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":126.821,\n" +
                "            \"y\": 37.454\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":126.835,\n" +
                "            \"y\":37.688\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/regions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void When_PostRegionWrongArea_Then_Response400() throws Exception {
        String request = "{\n" +
                "    \"area\":[\n" +
                "        {\n" +
                "            \"x\":126.835,\n" +
                "            \"y\":37.688\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":127.155,\n" +
                "            \"y\":37.702\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":127.184,\n" +
                "            \"y\": 37.474\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":126.821,\n" +
                "            \"y\": 37.454\n" +
                "        },\n" +
                "        {\n" +
                "            \"x\":126.835,\n" +
                "            \"y\":37.688\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/regions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
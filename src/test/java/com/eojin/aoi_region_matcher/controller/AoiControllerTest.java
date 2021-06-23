package com.eojin.aoi_region_matcher.controller;

import com.eojin.aoi_region_matcher.dto.response.PostAoiResponse;
import com.eojin.aoi_region_matcher.service.AoiService;
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


@WebMvcTest(AoiController.class)
public class AoiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AoiService aoiService;


    @Test
    public void When_PostAoiSuccess_Then_ReturnResponse_And_Response201() throws Exception{

        String request = "{\n" +
                "    \"name\":\"테스트AOI\",\n" +
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
        PostAoiResponse response = new PostAoiResponse(3);

        Mockito.when(aoiService.createAoi(any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/aois")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{id:3}"));
    }

    @Test
    public void When_PostAoiWithoutArea_Then_Response400() throws Exception{
        String request = "{\n" +
                "    \"name\":\"테스트AOI\",\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/aois")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void When_PostAoiWithoutName_Then_Response400() throws Exception{
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
                .post("/aois")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void When_PostAoiWithShortArea_Then_Response400() throws Exception{
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
                "            \"x\":126.835,\n" +
                "            \"y\":37.688\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/aois")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void When_GetNearestAoiSuccess_Then_Response200() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/aois?lat=1.1&long=2.2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void When_GetNearestAoiWithWrongParam_Then_Response400() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/aois?lat=fef&long=2.2"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

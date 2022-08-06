package com.lloydsbank.atms.api.lloydsbankatmsapi.controller;

import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm.Atm;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm.Location;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm.PostalAddress;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.response.AtmDetailsResponseDto;
import com.lloydsbank.atms.api.lloydsbankatmsapi.service.AtmService;
import com.lloydsbank.atms.api.lloydsbankatmsapi.utility.MapObjectToJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AtmController.class)
public class AtmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtmService atmService;

    AtmDetailsResponseDto atmDetailsMockResponse;

    @BeforeEach
    public void setUp(){

        List<Atm> atmList = new ArrayList<>();
        atmList.add(Atm.builder().identification("AAAAA1111")
                .location(Location.builder().postalAddress(PostalAddress.builder()
                        .postCode("TW209QP").country("UK").build()).build()).build());
        atmList.add(Atm.builder().identification("BBBB1111")
                .location(Location.builder().postalAddress(PostalAddress.builder()
                        .postCode("TW209QP").country("UK").build()).build()).build());
        atmList.add(Atm.builder().identification("CCCC1111")
                .location(Location.builder().postalAddress(PostalAddress.builder()
                        .postCode("TW209QP").country("UK").build()).build()).build());

        atmDetailsMockResponse = AtmDetailsResponseDto.builder()
                .identification("1111111111").atm(atmList).build();
    }

    @Test
    @DisplayName("Validate that the Get endpoint retrieves full Atm details by identification branch key")
    public void getAtmDetailsTest() throws Exception {
        Mockito.when(atmService.getAtmsByIdentification(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(atmDetailsMockResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/branch/atms?url=http://abc.com&identification=2345")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assertions.assertEquals(MapObjectToJson.mapToJson(atmDetailsMockResponse), result.getResponse().getContentAsString());

    }

}

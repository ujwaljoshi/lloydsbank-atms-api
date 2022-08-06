package com.lloydsbank.atms.api.lloydsbankatmsapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm.*;
import com.lloydsbank.atms.api.lloydsbankatmsapi.utility.MapObjectToJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@RestClientTest(AtmService.class)
public class AtmServiceTest {

    @Autowired
    private AtmService atmService;

    @MockBean
    private BranchService branchService;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    AtmsResponse mockAtmsResponse;
    List<Atm> atmList = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        List<Brand> brandList = new ArrayList<>();
        List<Data> dataList = new ArrayList<>();

        atmList.add(Atm.builder().identification("AAAAA1111")
                .location(Location.builder().postalAddress(PostalAddress.builder()
                        .postCode("TW209QP").country("UK").build()).build()).build());
        atmList.add(Atm.builder().identification("BBBB1111")
                .location(Location.builder().postalAddress(PostalAddress.builder()
                        .postCode("TW209QP").country("UK").build()).build()).build());
        atmList.add(Atm.builder().identification("CCCC1111")
                .location(Location.builder().postalAddress(PostalAddress.builder()
                        .postCode("TW209QP").country("UK").build()).build()).build());

        brandList.add(Brand.builder().brandName("Lloyds Bank").atm(atmList).build());

        dataList.add(Data.builder().brand(brandList).build());

        mockAtmsResponse = AtmsResponse.builder().meta(Meta.builder().lastUpdated("2022-08-01T13:11:13.228Z")
                .totalResults(72)
                .agreement("subject to terms and conditions")
                .license("open-license")
                .termsOfUse("terms").build())
                .data(dataList).build();
    }

    @Test
    @DisplayName("Validate that the API retrieves Atm details successfully from Lloyds Bank Open API /atms")
    public void getAllAtmsTest() throws JsonProcessingException {

        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("https://api.lloydsbank.com/open-banking/v2.2/atms"))
                .andRespond(MockRestResponseCreators.withSuccess(MapObjectToJson.mapToJson(mockAtmsResponse), MediaType.APPLICATION_JSON));

        AtmsResponse response = this.atmService.getAllAtms("https://api.lloydsbank.com/open-banking/v2.2/atms");

        Assertions.assertEquals(MapObjectToJson.mapToJson(mockAtmsResponse), MapObjectToJson.mapToJson(response));

    }


    @Test
    @DisplayName("Validate that the API retrieves list of Atm details by postcode")
    public void getAtmListTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method getAtmList = AtmService.class.getDeclaredMethod("getAtmList", String.class, AtmsResponse.class);
        getAtmList.setAccessible(true);
        List<Atm> atmsResponse = (List<Atm>) getAtmList.invoke(atmService,"TW209QP", mockAtmsResponse);

        Assertions.assertEquals(atmList, atmsResponse);

    }



}

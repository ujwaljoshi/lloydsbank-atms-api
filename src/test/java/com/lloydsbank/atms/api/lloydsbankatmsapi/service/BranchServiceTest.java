package com.lloydsbank.atms.api.lloydsbankatmsapi.service;

import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch.*;
import com.lloydsbank.atms.api.lloydsbankatmsapi.utility.MapObjectToJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
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
@RestClientTest(BranchService.class)
public class BranchServiceTest {

    @Autowired
    private BranchService branchService;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    BranchesResponse mockBranchesResponse;

    @BeforeEach
    public void setUp(){

        List<Branch> branchList = new ArrayList<>();
        List<Brand> brandList = new ArrayList<>();
        List<Data> dataList = new ArrayList<>();

        branchList.add(Branch.builder().identification("1111111111")
                .postalAddress(PostalAddress.builder()
                        .country("UK").postCode("TW209QP").build()).build());
        branchList.add(Branch.builder().identification("2222222222")
                .postalAddress(PostalAddress.builder()
                        .country("UK").postCode("TWQI20Y").build()).build());
        branchList.add(Branch.builder().identification("3333333333")
                .postalAddress(PostalAddress.builder()
                        .country("UK").postCode("E12UYXE").build()).build());

        brandList.add(Brand.builder().brandName("Lloyds Bank").branch(branchList).build());

        dataList.add(Data.builder().brand(brandList).build());


        mockBranchesResponse = BranchesResponse.builder().meta(Meta.builder().lastUpdated("2022-08-01T13:11:13.228Z")
                .totalResults(72)
                .agreement("subject to terms and conditions")
                .license("open-license")
                .termsOfUse("terms").build()).data(dataList).build();
    }

    @Test
    @DisplayName("Validate that the API retrieves Branch details successfully from Lloyds Bank Open API /branches")
    public void getAllBranchesTest() throws Exception{

        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("https://api.lloydsbank.com/open-banking/v2.2/branches"))
                .andRespond(MockRestResponseCreators.withSuccess(MapObjectToJson.mapToJson(mockBranchesResponse), MediaType.APPLICATION_JSON));

        BranchesResponse response = this.branchService.getAllBranches();

        Assertions.assertEquals(MapObjectToJson.mapToJson(mockBranchesResponse), MapObjectToJson.mapToJson(response));

    }

    @Test
    @DisplayName("Validate that the API retrieves Branch postcode by identification key")
    public void findBranchPostCodeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method findBranchPostCode = BranchService.class.getDeclaredMethod("findBranchPostCode", String.class, BranchesResponse.class);
        findBranchPostCode.setAccessible(true);
        String postCode = (String)findBranchPostCode.invoke(branchService, "1111111111", mockBranchesResponse);

        Assertions.assertEquals("TW209QP", postCode);
    }


}

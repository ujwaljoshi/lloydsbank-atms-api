package com.lloydsbank.atms.api.lloydsbankatmsapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm.Atm;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.atm.AtmsResponse;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.response.AtmDetailsResponseDto;
import com.lloydsbank.atms.api.lloydsbankatmsapi.exception.BranchIdentificationNotFoundException;
import com.lloydsbank.atms.api.lloydsbankatmsapi.exception.InvalidURLException;
import com.lloydsbank.atms.api.lloydsbankatmsapi.utility.PrettyPrintJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * The AtmService class is a service layer to retrieve the full ATM details
 * by Postcode from Lloyds Bank open API
 *  @author Ujwal Joshi
 */

@Service
@Slf4j
public class AtmService {

    @Autowired
    BranchService branchService;

    private final RestTemplate restTemplate;

    public AtmService(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    //getAtms methods takes two parameters url and identification to retrieve all the ATM details from open API by PostCode
    public AtmDetailsResponseDto getAtmsByIdentification(String url, String identification) throws BranchIdentificationNotFoundException, InvalidURLException, JsonProcessingException {
        List<Atm> atmList = new ArrayList<>();

        //Calls Branch Service to fetch the branch details by identification key
        String postCode = branchService.getBranchPostCodeByIdentification(identification);
        try{
            log.info("Calling Lloyds Bank Open API /atms");
            AtmsResponse atmsResponse = getAllAtms(url);

            //Filter out only matching Objects from Open API ATM service then it will add filtered objects in list  and return response object
            if (atmsResponse != null) {
                atmList = getAtmList(postCode, atmsResponse);
            }

            //Building AtmDetailsResponseDto Response payload and logging Response Object on Console
            AtmDetailsResponseDto atmDetailsResponse = AtmDetailsResponseDto.builder().identification(identification).atm(atmList).build();
            log.info("Details of ATM for Branch identification {} : {}", identification, PrettyPrintJson.printJsonResponseObject(atmDetailsResponse));
            return atmDetailsResponse;

        }catch (Exception e){
            //This will throw exception when an Invalid url is passed as a query parameter
            throw new InvalidURLException(e.getMessage());
        }

    }

    //Get all Atms from Lloyds Bank Open API
    public AtmsResponse getAllAtms(String url) {

        log.info("Get all Atms from Lloyds Bank Open API by URL {} ", url);
        return restTemplate.getForObject(url, AtmsResponse.class);
    }

    //Filter out only matching Objects from Open API ATM service then it will add filtered objects in list  and return response object
    private List<Atm> getAtmList(String postCode, AtmsResponse atmsResponse) {
        List<Atm> atmList = new ArrayList<>();
        atmsResponse.getData()
                .forEach(data -> data.getBrand()
                        .forEach(brand -> brand.getAtm()
                                .stream()
                                .filter(atm -> atm.getLocation().getPostalAddress().getPostCode().equals(postCode))
                                .forEach(atmList::add)));
        return atmList;
    }
}

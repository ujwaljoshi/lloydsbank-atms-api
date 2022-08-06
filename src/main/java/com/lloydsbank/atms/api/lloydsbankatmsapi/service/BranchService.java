package com.lloydsbank.atms.api.lloydsbankatmsapi.service;

import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch.Branch;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch.BranchesResponse;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch.Brand;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.branch.Data;
import com.lloydsbank.atms.api.lloydsbankatmsapi.exception.BranchIdentificationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The BranchService class is a service layer to retrieve the full Branch details
 * by Identification key from Lloyds Bank open API branch service
 *  @author Ujwal Joshi
 */

@Service
@Slf4j
public class BranchService {

    @Value("${lloyds_openapi_branches_url}")
    private String openApiBranchesUrl;

    private final RestTemplate restTemplate;

    public BranchService(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    //Get full Branch details from Open Api branch service and filter branches by identification key
    public String getBranchPostCodeByIdentification(String identification) throws BranchIdentificationNotFoundException {
        String postCode = null;
        log.info("Calling Lloyds Bank Open API /branches");
        BranchesResponse branchesResponse = getAllBranches();

        //Once the Branch by Identification is filtered retrieve postcode details and return to ATM Service method.
        log.info("Get branch details by identification {}", identification);
        if(branchesResponse != null){
            postCode = findBranchPostCode(identification, branchesResponse);
        }
        //If the Identification key is not found then postcode remain null
        //Here throwing an exception to let user know that identification key doesn't exist in Branch Open API service
        if (postCode != null) {
            return postCode;
        } else {
            log.info("Branch identification key {} is not found in Lloyds Bank Open API", identification);
            throw new BranchIdentificationNotFoundException(String.format("Branch identification key %s is not found in Lloyds Bank Open API", identification));
        }
    }

    //Get full Branch details from Open Api branch service
    public BranchesResponse getAllBranches(){
        log.info("Get all branches from Lloyds Bank Open API");
        return restTemplate.getForObject(openApiBranchesUrl, BranchesResponse.class);
    }


    //Find Branch by Identification key, get the postcode details and return to getBranches method.
    private String findBranchPostCode(String identification, BranchesResponse branchesResponse) {
        for(Data data : branchesResponse.getData()){
            for(Brand brand : data.getBrand()){
                for(Branch branch: brand.getBranch()){
                    if(branch.getIdentification().equals(identification)){
                            return branch.getPostalAddress().getPostCode();
                    }
                }
            }
        }
        return null;
    }
}

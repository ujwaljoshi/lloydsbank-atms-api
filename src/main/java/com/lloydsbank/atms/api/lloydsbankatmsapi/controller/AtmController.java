package com.lloydsbank.atms.api.lloydsbankatmsapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.response.AtmDetailsResponseDto;
import com.lloydsbank.atms.api.lloydsbankatmsapi.exception.BranchIdentificationNotFoundException;
import com.lloydsbank.atms.api.lloydsbankatmsapi.exception.InvalidURLException;
import com.lloydsbank.atms.api.lloydsbankatmsapi.service.AtmService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The AtmController class offers a http GET endpoint to retrieve full details of Lloyds Bank ATMs by the Branch Identification key
 *  @author Ujwal Joshi
 */

@RestController
@RequestMapping("/api/v1/branch")
@OpenAPIDefinition(info = @Info(
        title = "LLOYDSBANK-ATMS-API",
        version = "v1.0.0",
        description = "The purpose of this api is to expose a GET endpoint to retrieve full details of Lloyds Bank ATMs by the Branch Identification key"))
public class AtmController {

    @Autowired
    AtmService atmService;

    @GetMapping(value = "/atms", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "A GET endpoint to retrieve full ATM details by Branch identification key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public AtmDetailsResponseDto getAtmDetails(@RequestParam(name = "url") String url,
                                               @RequestParam(name = "identification") String identification) throws BranchIdentificationNotFoundException, InvalidURLException, JsonProcessingException {
        return atmService.getAtmsByIdentification(url, identification);
    }


}

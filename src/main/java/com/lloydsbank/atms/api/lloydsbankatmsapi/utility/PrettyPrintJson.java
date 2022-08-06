package com.lloydsbank.atms.api.lloydsbankatmsapi.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lloydsbank.atms.api.lloydsbankatmsapi.dto.response.AtmDetailsResponseDto;

/**
 * The PrettyPrintJson class is a Utility class which helps to print the Response json on console in Pretty format
 *  @author Ujwal Joshi
 */

public class PrettyPrintJson {

    public static String printJsonResponseObject(AtmDetailsResponseDto atmDetailsResponseDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(atmDetailsResponseDto);
    }
}

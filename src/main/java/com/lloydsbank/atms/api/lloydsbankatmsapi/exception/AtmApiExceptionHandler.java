package com.lloydsbank.atms.api.lloydsbankatmsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * The AtmApiExceptionHandler class is an exception adviser layer to help us handle checked exception.
 *  @author Ujwal Joshi
 */

@RestControllerAdvice
public class AtmApiExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BranchIdentificationNotFoundException.class)
    public Map<String, String> handleBranchServiceException(BranchIdentificationNotFoundException exception){

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message", exception.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidURLException.class)
    public Map<String, String> handleInvalidURLException(InvalidURLException exception){

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Invalid URL or requested endpoint does not exist) - error message", exception.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, String> handleMissingQueryParameterException(MissingServletRequestParameterException exception){

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message", "Required request parameter 'url' and/or 'identification' is not present");

        return errorMap;
    }


}

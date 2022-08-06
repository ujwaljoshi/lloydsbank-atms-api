package com.lloydsbank.atms.api.lloydsbankatmsapi.exception;

/**
 * The InvalidURLException class is a checked exception which is thrown
 * when Invalid url or host name doesn't exist in Lloyds Bank Open API
 *  @author Ujwal Joshi
 */

public class InvalidURLException extends Exception{

    public InvalidURLException(String message){
        super(message);
    }
}

package com.lloydsbank.atms.api.lloydsbankatmsapi.exception;

/**
 * The BranchIdentificationNotFoundException class is a checked exception which is thrown
 * when Identification key doesn't exist in Branches Open API
 *  @author Ujwal Joshi
 */

public class BranchIdentificationNotFoundException extends Exception{

    public BranchIdentificationNotFoundException(String message){
        super(message);
    }

}

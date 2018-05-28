package com.cp.exception;

import java.util.MissingResourceException;

public class CPNotResolvableException extends CPGameException {

    /**
     * 
     */
    private static final long serialVersionUID = 1186981706259160885L;


    public final static String NO_SOLUTION = "ex.no.solution";
    
    public CPNotResolvableException(String message) {
        super(message);
    }
    
    public CPNotResolvableException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
    @Override
    public String getLocalizedMessage() {
        String message;
        try {
            message = labels.getString(getMessage()); 
        } catch (MissingResourceException e) {
            message = "No resource for " + getMessage() + " key";
        }
        return message;
    }

}

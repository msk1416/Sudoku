package com.cp.exception;

import java.util.MissingResourceException;

public class CPValueOutOfBoundsException extends CPBoardException {

    /**
     * 
     */
    private static final long serialVersionUID = 2764737780818469829L;
    public static final String VALUE_OUT_OF_BOUNDS = "ex.value.out.of.bounds";
    
    public CPValueOutOfBoundsException(String message) {
        super(message);
    }
    public CPValueOutOfBoundsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    @Override
    public String getLocalizedMessage() {
        String message;
        try {
            message = labels.getString(getMessage()); 
        } catch (MissingResourceException e) {
            message = "No resource for " + getMessage() + "key";
        }
        return message;
    }
}

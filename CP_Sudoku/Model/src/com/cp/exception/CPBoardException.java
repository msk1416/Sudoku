package com.cp.exception;

import java.util.MissingResourceException;

public class CPBoardException extends CPGameException {

    /**
     * 
     */
    private static final long serialVersionUID = -8367202950397999289L;

    public static final String FILL_BOARD_GENERAL = "ex.fill.board";
    public CPBoardException(String message) {
        super(message);
    }
    
    public CPBoardException(String message, Throwable throwable) {
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

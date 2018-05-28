package com.cp.exception;

public class CPGameException extends CPException {

    /**
     * 
     */
    private static final long serialVersionUID = -5433917546334383197L;

    public CPGameException(String message) {
        super(message);
    }
    
    public CPGameException(String message, Throwable throwable) {
        super(message, throwable);
    }

}

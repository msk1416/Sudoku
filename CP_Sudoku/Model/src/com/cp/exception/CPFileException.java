package com.cp.exception;

import java.util.MissingResourceException;

public class CPFileException extends CPException {
    /**
     * 
     */
    private static final long serialVersionUID = -3880044302563697542L;
    public final static String CORRUPTED_FILE = "ex.corrupted.file";
    public final static String READ_ERR = "ex.read.file.err";
    public final static String WRITE_ERR = "ex.write.file.err";
    public CPFileException(String message) {
        super(message);
    }

    public CPFileException(String message, Throwable throwable) {
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

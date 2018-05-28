package com.cp.exception;

import java.util.Locale;
import java.util.ResourceBundle;

import application.Main;

/**
 * 
 * @author sergi
 *
 */
public class CPException extends Exception {

    private static final long serialVersionUID = -7555702625342502398L;
    public static ResourceBundle labels;
    public CPException(String message) {
        super(message);
        if (Main.currentLocale == null) 
            labels = ResourceBundle.getBundle("SudokuBundle", Locale.ENGLISH);
        else {
            if (!labels.getLocale().equals(Main.currentLocale))
                labels = ResourceBundle.getBundle("SudokuBundle", Main.currentLocale);
        }
    }
    
    public CPException(String message, Throwable throwable) {
        super(message, throwable);
        if (Main.currentLocale == null) 
            labels = ResourceBundle.getBundle("SudokuBundle", Locale.ENGLISH);
        else {
            if (!labels.getLocale().equals(Main.currentLocale))
                labels = ResourceBundle.getBundle("SudokuBundle", Main.currentLocale);
        }
    }
}

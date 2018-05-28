package com.cp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cp.elems.SudokuField;
import com.cp.exception.CPValueOutOfBoundsException;

class SudokuFieldTest {

    @Test
    void testSudokuFieldConstructorOk() {
        int testValue = 5;
        SudokuField field = null;
        try {
            field = new SudokuField(testValue);
        } catch (CPValueOutOfBoundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(testValue, field.getFieldValue());
    }
    
    @Test
    void testSudokuFieldConstructorOutOfBounds() {
        int testValue = 15;
        assertThrows(CPValueOutOfBoundsException.class, () -> { SudokuField field = new SudokuField(testValue); });
    }
    
    @Test
    void testSetFieldValueOk() {
        SudokuField field = new SudokuField();
        int testValue = 5;
        try {
            field.setFieldValue(testValue);
        } catch (CPValueOutOfBoundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(testValue, field.getFieldValue());
    }
    
    @Test
    void testSetFieldValueSetOutOfBounds() {
        int testValue = 15;
        SudokuField field = new SudokuField();
        assertThrows(CPValueOutOfBoundsException.class, () -> { field.setFieldValue(testValue); });
    }

}

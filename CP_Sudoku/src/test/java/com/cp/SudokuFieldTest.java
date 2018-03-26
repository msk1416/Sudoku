package com.cp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cp.elems.SudokuField;

class SudokuFieldTest {

    @Test
    void testSudokuFieldConstructorOk() {
        int testValue = 5;
        SudokuField field = new SudokuField(testValue);
        assertEquals(testValue, field.getFieldValue());
    }
    
    @Test
    void testSudokuFieldConstructorNonValid() {
        int testValue = 15;
        SudokuField field = new SudokuField(testValue);
        assertNotEquals(testValue, field.getFieldValue());
    }
    
    @Test
    void testSetFieldValueOk() {
        SudokuField field = new SudokuField();
        int testValue = 5;
        field.setFieldValue(testValue);
        assertEquals(testValue, field.getFieldValue());
    }
    
    @Test
    void testSetFieldValueNonValid() {
        int testValue = 15;
        SudokuField field = new SudokuField();
        field.setFieldValue(testValue);
        assertNotEquals(testValue, field.getFieldValue());
    }

}

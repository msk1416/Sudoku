package com.cp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cp.elems.SudokuField;
import com.cp.elems.SudokuLine;
import com.cp.elems.SudokuRow;

class SudokuLineTest {

    @Test
    void testSetLine() {
        SudokuField[] testCorrectValues = {new SudokuField(2), new SudokuField(3), 
                new SudokuField(4), new SudokuField(6), 
                new SudokuField(5), new SudokuField(8), 
                new SudokuField(1), new SudokuField(7), 
                new SudokuField(9)};
        SudokuRow row = new SudokuRow();
        row.setLine(testCorrectValues);
        assertEquals(testCorrectValues, row.getLine());
    }

    @Test
    void testVerify() {
        SudokuField[] testCorrectValues = {new SudokuField(2), new SudokuField(3), 
                                    new SudokuField(4), new SudokuField(6), 
                                    new SudokuField(5), new SudokuField(8), 
                                    new SudokuField(1), new SudokuField(7), 
                                    new SudokuField(9)};
        SudokuField[] testFailValues = {new SudokuField(2), new SudokuField(3), 
                                        new SudokuField(2), new SudokuField(6), 
                                        new SudokuField(5), new SudokuField(8), 
                                        new SudokuField(1), new SudokuField(7), 
                                        new SudokuField(9)};
        SudokuRow validRow = new SudokuRow();
        validRow.setLine(testCorrectValues);
        SudokuRow failRow = new SudokuRow();
        failRow.setLine(testFailValues);
        assertTrue(validRow.verify());
        assertFalse(failRow.verify());
    }

}

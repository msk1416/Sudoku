package com.cp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.cp.elems.SudokuField;
import com.cp.elems.SudokuLine;
import com.cp.elems.SudokuRow;

class SudokuLineTest {

    @Test
    void testSetLine() {
        ArrayList<SudokuField> listTest = new ArrayList<SudokuField>(9);
        listTest.add(new SudokuField(2));
        listTest.add(new SudokuField(3));
        listTest.add(new SudokuField(4));
        listTest.add(new SudokuField(6));
        listTest.add(new SudokuField(5));
        listTest.add(new SudokuField(8));
        listTest.add(new SudokuField(1));
        listTest.add(new SudokuField(7));
        listTest.add(new SudokuField(9));
        SudokuRow row = new SudokuRow();
        row.setLine(listTest);
        assertEquals(listTest, row.getLine());
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
        validRow.setLine(Arrays.asList(testCorrectValues));
        SudokuRow failRow = new SudokuRow();
        failRow.setLine(Arrays.asList(testFailValues));
        assertTrue(validRow.verify());
        assertFalse(failRow.verify());
    }

}

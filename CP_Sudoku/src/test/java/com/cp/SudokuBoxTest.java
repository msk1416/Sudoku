package com.cp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cp.elems.SudokuBox;
import com.cp.elems.SudokuField;

class SudokuBoxTest {

    @Test
    void testVerify() {
        int[] testCorrectValues = {2, 3, 4, 6, 5, 8, 1, 7, 9};
        SudokuBox testingBox = new SudokuBox(testCorrectValues);
        assertTrue(testingBox.verify());
    }

}

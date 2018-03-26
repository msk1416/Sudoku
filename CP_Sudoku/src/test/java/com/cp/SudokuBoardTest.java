package com.cp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cp.elems.SudokuBoard;
import com.cp.elems.SudokuBox;
import com.cp.elems.SudokuColumn;
import com.cp.elems.SudokuField;
import com.cp.elems.SudokuLine;
import com.cp.elems.SudokuRow;

class SudokuBoardTest {

    @Test
    void testSetOk() {
        SudokuBoard board = new SudokuBoard();
        board.set(3, 3, 5);
        assertEquals(5, board.get(3, 3));
    }
    
    @Test 
    void testSetNonValid() {
        SudokuBoard board = new SudokuBoard();
        board.set(3, 3, 19);
        assertNotEquals(19, board.get(3, 3));
    }
    
    @Test
    void testGetRow() {
        SudokuBoard board = new SudokuBoard();
        board.set(2, 0, 1);
        board.set(2, 1, 2);
        board.set(2, 2, 3);
        board.set(2, 3, 4);
        board.set(2, 4, 5);
        board.set(2, 5, 6);
        board.set(2, 6, 7);
        board.set(2, 7, 8);
        board.set(2, 8, 9);
        SudokuField[] testCorrectValues = {new SudokuField(1), new SudokuField(2), 
                new SudokuField(3), new SudokuField(4), 
                new SudokuField(5), new SudokuField(6), 
                new SudokuField(7), new SudokuField(8), 
                new SudokuField(9)};
        SudokuRow testRow = new SudokuRow();
        testRow.setLine(testCorrectValues);
        assertTrue(checkEqualLines(testRow, board.getRow(2)));
    }
    
    @Test
    void testGetColumn() {
        SudokuBoard board = new SudokuBoard();
        board.set(0, 4, 1);
        board.set(1, 4, 2);
        board.set(2, 4, 3);
        board.set(3, 4, 4);
        board.set(4, 4, 5);
        board.set(5, 4, 6);
        board.set(6, 4, 7);
        board.set(7, 4, 8);
        board.set(8, 4, 9);
        SudokuField[] testCorrectValues = {new SudokuField(1), new SudokuField(2), 
                new SudokuField(3), new SudokuField(4), 
                new SudokuField(5), new SudokuField(6), 
                new SudokuField(7), new SudokuField(8), 
                new SudokuField(9)};
        SudokuColumn testCol = new SudokuColumn();
        testCol.setLine(testCorrectValues);
        assertTrue(checkEqualLines(testCol, board.getColumn(4)));
    }
    
    @Test
    void testGetBox() {
        SudokuBoard board = new SudokuBoard();
        board.set(0, 0, 1);
        board.set(0, 1, 2);
        board.set(0, 2, 3);
        board.set(1, 0, 4);
        board.set(1, 1, 5);
        board.set(1, 2, 6);
        board.set(2, 0, 7);
        board.set(2, 1, 8);
        board.set(2, 2, 9);
        int[] testCorrectValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SudokuBox testBox = new SudokuBox(testCorrectValues);
        assertTrue(checkEqualBoxes(testBox, board.getBox(0, 0)));
    }
    
    private boolean checkEqualLines(SudokuLine l1, SudokuLine l2) {
        int i = 0;
        while (i < l1.getSize()) {
            if (l1.getLine()[i].getFieldValue() != l2.getLine()[i].getFieldValue()) {
                return false;
            }
            i++;
        }
        return true;
    }
    
    private boolean checkEqualBoxes(SudokuBox b1, SudokuBox b2) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b1.get(i, j).getFieldValue() != b2.get(i, j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

}

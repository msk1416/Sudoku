package com.cp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cp.dao.SudokuBoardDaoFactory;
import com.cp.elems.SudokuBoard;
import com.cp.solver.BacktrackingSudokuSolver;

class DaoTest {

    @Test
    void testReadWrite() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard result = solver.fillBoard();
        SudokuBoardDaoFactory.getFileDao("output.cpsb").write(result);
        
        SudokuBoard testRead = SudokuBoardDaoFactory.getFileDao("output.cpsb").read();
        assertTrue(equalBoards(result, testRead));
    }
    
    private boolean equalBoards(SudokuBoard a, SudokuBoard b) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a.get(i, j) != b.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

}

package com.cp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import com.cp.elems.SudokuBoard;
import com.cp.exception.CPBoardException;
import com.cp.exception.CPNotResolvableException;
import com.cp.exception.CPValueOutOfBoundsException;
import com.cp.solver.BacktrackingSudokuSolver;

import application.MainController;

class SudokuSolverTest {
    private final int N = 9;
    final static Logger logger = Logger.getLogger(SudokuSolverTest.class);
    
    @Test
    public void testCorrectness() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = null;
        try {
            testBoard = testSolver.fillBoard();
        } catch (CPBoardException | CPNotResolvableException e) {
            logger.error(e.getLocalizedMessage());
            logger.error(e.getStackTrace());
        }
        //check rows and columns
        for (int i=0; i<N; i++) {
            //ith row
            assertTrue(testBoard.getRow(i).verify());
            //ith column
            assertTrue(testBoard.getColumn(i).verify());
        }
        //check each 3x3 box
        for (int i=0; i<N; i+=3) {
            for (int j=0; j<N; j+=3) {
                assertTrue(testBoard.getBox(i, j).verify());
            }
        }
    }
    

    @Test
    public void testNoRepeatedSolutions() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard boardTest1;
        try {
            boardTest1 = testSolver.fillBoard();
            SudokuBoard boardTest2 = testSolver.fillBoard();
            assertFalse(equalBoards(boardTest1, boardTest2));
        } catch (CPBoardException | CPNotResolvableException e) {
            logger.error(e.getLocalizedMessage());
            logger.error(e.getStackTrace());
        }
        
    }

    private boolean equalBoards(SudokuBoard a, SudokuBoard b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a.get(i, j) != b.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}

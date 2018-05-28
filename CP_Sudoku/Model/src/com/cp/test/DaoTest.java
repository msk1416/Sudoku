package com.cp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.cp.dao.SudokuBoardDaoFactory;
import com.cp.elems.SudokuBoard;
import com.cp.exception.CPBoardException;
import com.cp.exception.CPFileException;
import com.cp.exception.CPNotResolvableException;
import com.cp.solver.BacktrackingSudokuSolver;

class DaoTest {
    final static Logger logger = Logger.getLogger(DaoTest.class);
    @Test
    void testReadWrite() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard result = null;
        try {
            result = solver.fillBoard();
            SudokuBoardDaoFactory.getFileDao("output.cpsb").write(result);
            SudokuBoard testRead = SudokuBoardDaoFactory.getFileDao("output.cpsb").read();
            assertTrue(equalBoards(result, testRead));
        } catch (CPBoardException | CPFileException | CPNotResolvableException e) {
            logger.error(e.getLocalizedMessage());
            logger.error(e.getStackTrace());
        }
    }
    
    @Test
    void testReadCorruptFile() {
        assertThrows(CPFileException.class, () -> { SudokuBoardDaoFactory.getFileDao("C:\\Users\\sergi\\Desktop\\err.cpsb").read(); });
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

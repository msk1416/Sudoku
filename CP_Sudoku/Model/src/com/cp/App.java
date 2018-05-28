package com.cp;

import org.apache.log4j.Logger;

import com.cp.dao.SudokuBoardDaoFactory;
import com.cp.elems.SudokuBoard;
import com.cp.exception.CPBoardException;
import com.cp.exception.CPFileException;
import com.cp.exception.CPNotResolvableException;
import com.cp.exception.CPValueOutOfBoundsException;
import com.cp.solver.BacktrackingSudokuSolver;

import application.MainController;


/**
 * Hello world!
 *
 */
public class App {
    final static Logger logger = Logger.getLogger(MainController.class);
    public static void main(final String[] args) {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        
        try {
            SudokuBoardDaoFactory.getFileDao("sb1.cpsb").write(solver.fillBoard());
            SudokuBoard testRead = SudokuBoardDaoFactory.getFileDao("sb1.cpsb").read();
            testRead.print();
        } catch (CPBoardException | CPFileException | CPNotResolvableException e) {
            logger.error(e.getLocalizedMessage());
            logger.error(e.getStackTrace());
        }
        
        
    }
}

package com.cp;

import com.cp.dao.SudokuBoardDaoFactory;
import com.cp.elems.SudokuBoard;
import com.cp.solver.BacktrackingSudokuSolver;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(final String[] args) {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        
        SudokuBoardDaoFactory.getFileDao("output.dat").write(solver.fillBoard());
        
        SudokuBoard testRead = SudokuBoardDaoFactory.getFileDao("output.dat").read();
        testRead.print();
        
    }
}

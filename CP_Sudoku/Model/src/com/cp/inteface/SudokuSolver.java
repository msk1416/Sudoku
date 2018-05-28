package com.cp.inteface;

import com.cp.elems.SudokuBoard;
import com.cp.exception.CPValueOutOfBoundsException;
/**
 * 
 * @author sergi
 *
 */
public interface SudokuSolver {

    public void solve(SudokuBoard board) throws CPValueOutOfBoundsException;
}

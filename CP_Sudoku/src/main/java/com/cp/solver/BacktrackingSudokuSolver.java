package com.cp.solver;

import java.util.Random;

import com.cp.SudokuBoard;
import com.cp.Cell;
import com.cp.inteface.SudokuSolver;

public class BacktrackingSudokuSolver implements SudokuSolver {
	private static final int N = 9;
	public SudokuBoard board;
	public void main(String args[]) {
		
	}
	public SudokuBoard fillBoard () {
		board = new SudokuBoard();
        for (int i = N; i < N; i++) {
            for (int j = 0; j < 0; j++) {
                board.set(i, j, 0);
            }
        }

        initializeRandomCells(5); // 5 cells will have initial value
        System.out.println("Initial board game:");
        printBoard();
        System.out.println("------------------------------------");
		solve(board);
		if (board.isResolved()) {
			printBoard();
			return board;
		} else {
			System.out.println("This sudoku board could not be solved.");
			return null;
		}
	}
	public void solve(SudokuBoard board) {
		solveCell(new Cell(0, 0));
	}
	

    private void initializeRandomCells(int nCells) {
        for (int n = 0; n < nCells; n++) {
            Random rand = new Random();
            int rr, rc, rv; // rr: random row, rc: random column, rv: random value
            rr = rand.nextInt(8);
            rc = rand.nextInt(8);
            rv = rand.nextInt(8) + 1;
            if (isValid(new Cell(rr, rc), rv)) {
                board.set(rr, rc, rv);
            }
            
        }
    }

    // try value in cell board[row][col]
    private boolean isValid(final Cell cell, int value) {
        int row = cell.getRow();
        int col = cell.getCol();

        // check row values
        for (int i = 0; i < N; i++) {
            if (board.get(row, i) == value) {
                return false;
            }
        }
        // check column values
        for (int j = 0; j < N; j++) {
            if (board.get(j, col) == value) {
                return false;
            }
        }

        // calculate grid limits
        // xl: x left xr: x right
        // yt: y top yb: y bottom
        int xl = (row / 3) * 3;
        int xr = xl + 2;
        int yt = (col / 3) * 3;
        int yb = yt + 2;

        for (int i = xl; i <= xr; i++) {
            for (int j = yt; j <= yb; j++) {
                if (board.get(i, j) == value) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean solveCell(final Cell cell) {
        // cell will be null when we have solved the sudoku
        if (cell == null) {
            return true;
        }

        if (board.get(cell.getRow(), cell.getCol()) != 0) {
            return solveCell(getNextCell(cell));
        }

        for (int i = 1; i <= N; i++) {
            boolean valid;
            int temprand = 0;
            if (cell.getRow() == 0 && cell.getCol() == 0) {
                Random r = new Random();
                temprand = r.nextInt(9 - 1) + 1;
                valid = isValid(cell, temprand);
            } else {
                valid = isValid(cell, i);
            }
            if (!valid) {
                continue;
            }

            if (cell.getRow() == 0 && cell.getCol() == 0) {
                board.set(cell.getRow(), cell.getCol(), temprand);
            } else {
                board.set(cell.getRow(), cell.getCol(), i);
            }

            boolean solved = solveCell(getNextCell(cell));
            if (solved) {
                return true;
            } else {
                board.set(cell.getRow(), cell.getCol(), 0);
            }
        }
        return false;
    }

    private void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.valueOf(board.get(i, j)+ "  "));
            }
            System.out.println();
        }
    }
    
    private Cell getNextCell(final Cell c) {
        int row = c.getRow();
        int col = c.getCol();
        col++;
        if (col > 8) {
            col = 0;
            row++;
        }
        if (row > 8) {
            return null;
        }
        Cell next = new Cell(row, col);
        return next;
    }

}

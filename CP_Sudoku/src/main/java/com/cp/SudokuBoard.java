package com.cp;

import java.util.Random;

public class SudokuBoard {
    static final int N = 9;
    private static int[][] board;

    public static int[][] fillBoard() {
        board = new int[N][N];
        for (int i = N; i < N; i++) {
            for (int j = 0; j < 0; j++) {
                board[i][j] = 0;
            }
        }

        initializeRandomCells(5); // 5 cells will have initial value
        System.out.println("Initial board game:");
        printBoard();
        System.out.println("------------------------------------");
        boolean solved = solve(new Cell(0, 0));
        if (!solved) {
            System.out.println("Sudoku couldn't be solved!");
        } else {
            printBoard();
        }
        return board;

    }

    private static void initializeRandomCells(int nCells) {
        for (int n = 0; n < nCells; n++) {
            Random rand = new Random();
            int rr, rc, rv; // rr: random row, rc: random column, rv: random value
            rr = rand.nextInt(8);
            rc = rand.nextInt(8);
            rv = rand.nextInt(8) + 1;
            if (isValid(new Cell(rr, rc), rv)) {
                board[rr][rc] = rv;
            }
            // board[rand.nextInt(8)][rand.nextInt(8)] = rand.nextInt(8)+1;
        }
    }

    // try value in cell board[row][col]
    private static boolean isValid(final Cell cell, int value) {
        int row = cell.row;
        int col = cell.col;

        // check row values
        for (int i = 0; i < N; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }
        // check column values
        for (int j = 0; j < N; j++) {
            if (board[j][col] == value) {
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
                if (board[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean solve(final Cell cell) {
        // cell will be null when we have solved the sudoku
        if (cell == null) {
            return true;
        }

        if (board[cell.row][cell.col] != 0) {
            return solve(getNextCell(cell));
        }

        for (int i = 1; i <= N; i++) {
            boolean valid;
            int temprand = 0;
            if (cell.row == 0 && cell.col == 0) {
                Random r = new Random();
                temprand = r.nextInt(9 - 1) + 1;
                valid = isValid(cell, temprand);
            } else {
                valid = isValid(cell, i);
            }
            if (!valid) {
                continue;
            }

            if (cell.row == 0 && cell.col == 0) {
                board[cell.row][cell.col] = temprand;
            } else {
                board[cell.row][cell.col] = i;
            }

            boolean solved = solve(getNextCell(cell));
            if (solved) {
                return true;
            } else {
                board[cell.row][cell.col] = 0;
            }
        }
        return false;
    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.valueOf(board[i][j]) + "  ");
            }
            System.out.println();
        }
    }

    static class Cell {
        int row, col;

        public Cell(int row, int col) {
            super();
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Cell [row=" + row + ", col=" + col + "]";
        }
    };

    static Cell getNextCell(final Cell c) {
        int row = c.row;
        int col = c.col;
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

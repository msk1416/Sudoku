package com.cp.elems;

import java.util.Arrays;

public class SudokuBoard {
    static final int N = 9;
    private int[][] board;
    private boolean resolved = false;

    public SudokuBoard() {
        board = new int[N][N];
    }

    public int[] getRow(int i) {
        return board[i];
    }

    public boolean isResolved() {
        //same as return checkBoard() but we avoid a never-used warning
        checkBoard();
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public int get(int x, int y) {
        int val = board[x][y];
        return val;
    }

    public void set(int x, int y, int value) {
        //value 0 will be inserted by the algorithm solver when backtracking, to reset de position
        if (value >= 0 && value <= 9) { 
            board[x][y] = value;
        }
    }

    private boolean checkBoard() {
        boolean ret = true;
        for (int i = 0; i < N; i++) {
            //ith row
            ret &= checkArrayIsValid(board[i]);
            //ith column
            int[] colArray = new int[N];
            for (int j = 0; j < N; j++) {
                colArray[j] = board[j][i];
            }
            ret &= checkArrayIsValid(colArray);
        }
        //check each 3x3 box
        for (int i = 0; i < N; i += 3) {
            for (int j = 0; j < N; j += 3) {
                int xl = i, xr = i + 2, yt = j, yb = j + 2;
                int[] boxArray = new int[N];
                int k = 0;
                for (int ii = xl; ii <= xr; ii++) { //put the elements of the box into an array
                    for (int jj = yt; jj <= yb; jj++) {
                        boxArray[k++] = board[ii][jj];
                    }
                }
                ret &= checkArrayIsValid(boxArray);
            }
        }
        resolved = ret;
        return ret;
    }

    private boolean checkArrayIsValid(final int[] arr) {
        //check either a row or a column is valid
        int[] copy = Arrays.copyOf(arr, N);
        Arrays.sort(copy);
        int[] validLine = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        return Arrays.equals(validLine, copy);
    }
}

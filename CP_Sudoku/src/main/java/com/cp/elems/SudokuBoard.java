package com.cp.elems;

public class SudokuBoard {
    static final int N = 9;
    private SudokuField[][] board;
    private boolean resolved = false;

    public SudokuBoard() {
        board = new SudokuField[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public SudokuRow getRow(int i) {
        SudokuRow row = new SudokuRow();
        row.setLine(board[i]);
        return row;
    }
    
    public SudokuColumn getColumn(int i) {
        SudokuField[] colArray = new SudokuField[N];
        for (int j = 0; j < N; j++) {
            colArray[j] = board[j][i];
        }
        SudokuColumn col = new SudokuColumn();
        col.setLine(colArray);
        return col;
    }
    
    public SudokuBox getBox(int i, int j) {
        int xl = (i / 3) * 3,
            xr = i + 2, 
            yt = (j / 3) * 3, 
            yb = j + 2;
        int[] boxArray = new int[N];
        int k = 0;
        for (int ii = xl; ii <= xr; ii++) { //put the elements of the box into an array
            for (int jj = yt; jj <= yb; jj++) {
                boxArray[k++] = board[ii][jj].getFieldValue();
            }
        }

        SudokuBox box = new SudokuBox(boxArray);
        return box;
        
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
        int val = board[x][y].getFieldValue();
        return val;
    }

    public void set(int x, int y, int value) {
        //value 0 will be inserted by the algorithm solver when backtracking, to reset de position
        if (value >= 0 && value <= 9) { 
            board[x][y].setFieldValue(value);
        }
    }

    private boolean checkBoard() {
        boolean ret = true;
        for (int i = 0; i < N; i++) {
            //ith row
            ret &= getRow(i).verify();
            //ith column
            ret &= getColumn(i).verify();
        }
        //check each 3x3 box
        for (int i = 0; i < N; i += 3) {
            for (int j = 0; j < N; j += 3) {
                ret &= getBox(i, j).verify();
            }
        }
        resolved = ret;
        return ret;
    }
}

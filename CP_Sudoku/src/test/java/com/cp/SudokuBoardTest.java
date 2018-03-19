package com.cp;

import java.util.Arrays;

import com.cp.elems.SudokuBoard;
import com.cp.solver.BacktrackingSudokuSolver;

import junit.framework.TestCase;

public class SudokuBoardTest extends TestCase {

    private final int N = SudokuBoard.N;
    private final int[] validLine = {1,2,3,4,5,6,7,8,9};
    public SudokuBoardTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCorrectness() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = testSolver.fillBoard();
        //check rows and columns
        for (int i=0; i<N; i++) {
            //ith row
            assertTrue(arrayIsValid(testBoard.getRow(i)));
            //ith column
            int[] colArray = new int[N];
            for (int j=0;j<N;j++)
                colArray[j] = testBoard.get(j, i);
            assertTrue(arrayIsValid(colArray));
        }
        //check each 3x3 box
        for (int i=0; i<N; i+=3) {
            for (int j=0; j<N; j+=3) {
                int xl = i, xr = i+2, yt = j, yb = j+2;
                int[] boxArray = new int[N];
                int k = 0;
                for(int ii=xl; ii<=xr;ii++) {//put the elements of the box into an array
                    for (int jj=yt; jj<=yb; jj++)
                        boxArray[k++] = testBoard.get(ii, jj);
                }
                assertTrue(arrayIsValid(boxArray));
            }
        }
    }

    private boolean arrayIsValid(int[] arr) {
        //check either a row or a column is valid
        int[] copy = Arrays.copyOf(arr, N);
        Arrays.sort(copy);
        return Arrays.equals(validLine, copy);
    }

    public void testNoRepeatedSolutions() {
        BacktrackingSudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard boardTest1 = testSolver.fillBoard();
        SudokuBoard boardTest2 = testSolver.fillBoard();
        assertFalse(equalBoards(boardTest1, boardTest2));
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

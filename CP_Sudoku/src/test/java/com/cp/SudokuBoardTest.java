package com.cp;

import java.util.Arrays;

import com.cp.SudokuBoard;

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
        int[][] testBoard = SudokuBoard.fillBoard();
        //check rows and columns
        for (int i=0; i<N; i++) {
            //ith row
            assertTrue(arrayIsValid(testBoard[i]));
            //ith column
            int[] colArray = new int[N];
            for (int j=0;j<N;j++)
                colArray[j] = testBoard[j][i];
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
                        boxArray[k++] = testBoard[ii][jj];
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
        int[][] boardTest1 = SudokuBoard.fillBoard();
        int[][] boardTest2 = SudokuBoard.fillBoard();
        assertFalse(Arrays.equals(boardTest1, boardTest2));
    }

}

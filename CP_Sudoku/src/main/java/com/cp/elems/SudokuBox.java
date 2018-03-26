package com.cp.elems;

import java.util.Arrays;
import java.util.List;

public class SudokuBox {
    private List<List<SudokuField>> box;
    private final int N = 3;
    
    public SudokuBox() {
        //box = new SudokuField[N][N];
        box = Arrays.asList(Arrays.asList(new SudokuField[N]));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                box.get(i).set(j, new SudokuField());
            }
        }
    }

    /**
     * Initialize box from array of values
     * @param line array with values to be set, ordered line
     */
    public SudokuBox(final int[] line) {
        box = Arrays.asList(Arrays.asList(new SudokuField[N]));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                box.get(i).set(j, new SudokuField(line[i * N + j]));
            }
        }
    }
    public boolean verify() {
        int[] boxArray = new int[N * N];
        int k = 0;
        for (int i = 0; i < N; i++) { //put the elements of the box into an array
            for (int j = 0; j < N; j++) {
                boxArray[k++] = box.get(i).get(j).getFieldValue();
            }
        }
        //int[] copy = Arrays.copyOf(line, N);
        Arrays.sort(boxArray);
        int[] validLine = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        return Arrays.equals(validLine, boxArray);
    }
}

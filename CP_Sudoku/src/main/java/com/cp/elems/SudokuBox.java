package com.cp.elems;

import java.util.Arrays;
import java.util.List;

public class SudokuBox extends SudokuLine {

    public SudokuField getElementAsMatrix(int i, int j) {
        return super.getLine().get(i * 3 + j);
    }
}

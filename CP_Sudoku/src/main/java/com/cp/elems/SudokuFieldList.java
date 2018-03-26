package com.cp.elems;

import java.util.ArrayList;
import java.util.Arrays;

public class SudokuFieldList {
    public ArrayList<SudokuField> list;
    private static final int N = 9;
    public SudokuFieldList() {
        list = (ArrayList<SudokuField>) Arrays.asList(new SudokuField[N]);
    }
}

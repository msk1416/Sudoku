package com.cp.elems;

import java.util.Arrays;

/**
 * 
 * @author sergi
 *
 */
public abstract class SudokuLine {
    private int[] line;
    private final int N = 9;
    
    /**
     * @return the line
     */
    public int[] getLine() {
        return line;
    }
    
    /**
     * @param line the line to set
     */
    public void setLine(int[] line) {
        this.line = line;
    }
    
    public boolean verify() {
        int[] copy = Arrays.copyOf(line, N);
        Arrays.sort(copy);
        int[] validLine = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        return Arrays.equals(validLine, copy);
    }

    /**
     * @return the n
     */
    public int getSize() {
        return N;
    }
}

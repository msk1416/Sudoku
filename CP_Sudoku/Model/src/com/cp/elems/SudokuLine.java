package com.cp.elems;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 
 * @author sergi
 *
 */
public abstract class SudokuLine {
    private List<SudokuField> line;
    private final int N = 9;
    
    public SudokuLine() {
        line = Arrays.asList(new SudokuField[N]);
        for (int i = 0; i < N; i++) {
            line.set(i, new SudokuField());
        }
    }
    /**
     * @return the line
     */
    public List<SudokuField> getLine() {
        return line;
    }
    
    /**
     * @param line the line to set
     */
    public void setLine(final List<SudokuField> line) {
        this.line = line;
    }
    
    private int[] toArray() {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = line.get(i).getFieldValue();
        }
        return arr;
    }
    
    public boolean verify() {
        int[] copy = Arrays.copyOf(toArray(), N);
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
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("line", line)
                .add("N", N)
                .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.line,
                this.N);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuLine other = (SudokuLine) obj;
        return Objects.equal(this.line, other.line)
                && Objects.equal(this.N, other.N);
    }
    
    
}

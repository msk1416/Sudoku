package com.cp.elems;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuColumn extends SudokuLine {
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("line", getLine())
                .add("N", getSize())
                .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.getLine(), 
                this.getSize());
    }

}

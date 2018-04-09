package com.cp.elems;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuRow extends SudokuLine {
    
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
    
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuRow other = (SudokuRow) obj;
        return Objects.equal(this.getLine(), other.getLine())
                && Objects.equal(this.getSize(), other.getSize());
    }
}

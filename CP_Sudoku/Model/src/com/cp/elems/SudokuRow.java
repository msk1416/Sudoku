package com.cp.elems;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuRow extends SudokuLine implements Cloneable {
    
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
    
    @Override
    protected SudokuRow clone() throws CloneNotSupportedException {
        SudokuRow clone = new SudokuRow();
        List<SudokuField> listClone = new ArrayList<SudokuField>();
        for (int i = 0; i < this.getLine().size(); i++) {
            listClone.set(i, this.getLine().get(i).clone());
        }
        clone.setLine(listClone);
        return clone;
    }
}

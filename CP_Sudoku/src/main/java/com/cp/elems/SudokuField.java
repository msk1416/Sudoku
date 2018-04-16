package com.cp.elems;

import java.io.Serializable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuField implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7945057612502061554L;
    private int value;
    
    public SudokuField() {
        this.value = 0;
    }
    
    public SudokuField(int value) {
        if (value >= 0 && value <= 9) {
            this.value = value;
        } else {
            this.value = 0;
        }
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        if (value >= 0 && value <= 9) {
            this.value = value;
        } else {
            this.value = 0;
        }
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
            .add("value", value)
            .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) { 
            return false;
        }
        final SudokuField other = (SudokuField) obj;
        return Objects.equal(this.value, other.value);
    }
}

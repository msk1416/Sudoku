package com.cp.elems;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuField {
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
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final SudokuField other = (SudokuField) obj;
        return Objects.equal(this.value, other.value);
    }
}

package com.cp.elems;

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
    
}

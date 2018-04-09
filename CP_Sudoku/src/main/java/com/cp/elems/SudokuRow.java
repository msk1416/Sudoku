package com.cp.elems;

<<<<<<< HEAD


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
=======
public class SudokuRow extends SudokuLine {
    
    
>>>>>>> branch 'exercise-6' of https://902382@atlas.it.p.lodz.pl/bitbucket/scm/comprog2018/comprog18.git
}

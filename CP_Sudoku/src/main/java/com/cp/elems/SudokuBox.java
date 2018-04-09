<<<<<<< HEAD
package com.cp.elems;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuBox extends SudokuLine {

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
=======
package com.cp.elems;

public class SudokuBox extends SudokuLine {

}
>>>>>>> branch 'exercise-6' of https://902382@atlas.it.p.lodz.pl/bitbucket/scm/comprog2018/comprog18.git

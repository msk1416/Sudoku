package com.cp.elems;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SudokuBoard implements Serializable, Cloneable {
    /**
     * 
     */
    private static final long serialVersionUID = -3162808268825390492L;
    static final int N = 9;
    private ArrayList<ArrayList<SudokuField>> board;
    private boolean resolved = false;

    public SudokuBoard() {
        board = new ArrayList<ArrayList<SudokuField>>(N);
        for (int i = 0; i < N; i++) {
            board.add(i, new ArrayList<SudokuField>(N));
            for (int j = 0; j < N; j++) {
                board.get(i).add(j, new SudokuField());
            }
        }
    }

    public SudokuRow getRow(int i) {
        SudokuRow row = new SudokuRow();
        row.setLine(board.get(i));
        return row;
    }
    
    public SudokuColumn getColumn(int i) {
        SudokuField[] colArray = new SudokuField[N];
        for (int j = 0; j < N; j++) {
            colArray[j] = board.get(j).get(i);
        }
        SudokuColumn col = new SudokuColumn();
        col.setLine(Arrays.asList(colArray));
        return col;
    }
    
    public SudokuBox getBox(int i, int j) {
        int xl = (i / 3) * 3,
            xr = i + 2, 
            yt = (j / 3) * 3, 
            yb = j + 2;
        SudokuField[] boxArray = new SudokuField[N];
        int k = 0;
        for (int ii = xl; ii <= xr; ii++) { //put the elements of the box into an array
            for (int jj = yt; jj <= yb; jj++) {
                boxArray[k++] = board.get(ii).get(jj);
            }
        }

        SudokuBox box = new SudokuBox();
        box.setLine(Arrays.asList(boxArray));
        return box;
        
    }

    public boolean isResolved() {
        //same as return checkBoard() but we avoid a never-used warning
        checkBoard();
        return resolved;
    }

    public int get(int x, int y) {
        int val = board.get(x).get(y).getFieldValue();
        return val;
    }

    public void set(int x, int y, int value) {
        //value 0 will be inserted by the algorithm solver when backtracking, to reset de position
        if (value >= 0 && value <= 9) { 
            board.get(x).get(y).setFieldValue(value);
        }
    }
    
    public boolean existEmptyCells() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board.get(i).get(j).getFieldValue() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkBoard() {
        boolean ret = true;
        for (int i = 0; i < N; i++) {
            //ith row
            ret &= getRow(i).verify();
            //ith column
            ret &= getColumn(i).verify();
        }
        //check each 3x3 box
        for (int i = 0; i < N; i += 3) {
            for (int j = 0; j < N; j += 3) {
                ret &= getBox(i, j).verify();
            }
        }
        resolved = ret;
        return ret;
    }
    
    public void print() {
        System.out.println("-------------------------");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.valueOf(board.get(i).get(j).getFieldValue() +  "  "));
            }
            System.out.println();
        }
        
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("board", board)
                .add("resolved", resolved)
                .add("N", N)
                .toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.board,
                this.resolved,
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
        final SudokuBoard other = (SudokuBoard) obj;
        return this.boardEqual(other)
                && Objects.equal(this.resolved, other.resolved)
                && Objects.equal(this.N, other.N);
    }
    
    @Override
    protected SudokuBoard clone() throws CloneNotSupportedException {
        byte[] object;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(this);
            object = baos.toByteArray();
            
        } catch (IOException ioe) {
            System.out.println(ioe);
            return null;
        }
        
        try (ByteArrayInputStream bais = new ByteArrayInputStream(object);
                ObjectInputStream ois = new ObjectInputStream(bais);) {
            
            SudokuBoard clone = (SudokuBoard) ois.readObject();
            return (SudokuBoard) clone;
        } catch (IOException | ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }
    
    public boolean boardEqual(SudokuBoard second) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!this.board.get(i).get(j).equals(second.board.get(i).get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

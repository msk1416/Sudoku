package com.cp.elems;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import application.MainController;

public class SudokuColumn extends SudokuLine implements Cloneable {
    final static Logger logger = Logger.getLogger(SudokuColumn.class);
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
        final SudokuColumn other = (SudokuColumn) obj;
        return Objects.equal(this.getLine(), other.getLine())
                && Objects.equal(this.getSize(), other.getSize());
    }
    
    @Override
    protected SudokuColumn clone() throws CloneNotSupportedException {
        byte[] object;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(this);
            object = baos.toByteArray();
            
        } catch (IOException ioe) {
            logger.debug(ioe);
            return null;
        }
        
        try (ByteArrayInputStream bais = new ByteArrayInputStream(object);
                ObjectInputStream ois = new ObjectInputStream(bais);) {
            
            SudokuColumn clone = (SudokuColumn) ois.readObject();
            return (SudokuColumn) clone;
        } catch (IOException | ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }
}

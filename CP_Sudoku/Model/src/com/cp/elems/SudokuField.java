package com.cp.elems;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

import com.cp.exception.CPValueOutOfBoundsException;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import application.MainController;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    /**
     * 
     */
    final static Logger logger = Logger.getLogger(SudokuField.class);
    private static final long serialVersionUID = -7945057612502061554L;
    private int value;
    
    public SudokuField() {
        this.value = 0;
    }
    
    public SudokuField(int value) throws CPValueOutOfBoundsException {
        setFieldValue(value);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) throws CPValueOutOfBoundsException {
        if (value >= 0 && value <= 9) {
            this.value = value;
        } else {
            this.value = 0;
            CPValueOutOfBoundsException ex = new CPValueOutOfBoundsException(CPValueOutOfBoundsException.VALUE_OUT_OF_BOUNDS);
            throw ex;
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
    
    @Override
    protected SudokuField clone() throws CloneNotSupportedException {
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
            
            SudokuField clone = (SudokuField) ois.readObject();
            return (SudokuField) clone;
        } catch (IOException | ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }

    @Override
    public int compareTo(SudokuField arg0) {
        if (this.value < arg0.getFieldValue()) {
            return -1;
        } else if (this.value == arg0.getFieldValue()) {
            return 0;
        } else {
            return 1;
        }
    }
}

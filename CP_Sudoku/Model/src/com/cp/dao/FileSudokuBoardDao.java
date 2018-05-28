package com.cp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.cp.elems.SudokuBoard;
import com.cp.exception.CPFileException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;
    private static final String EXT = ".cpsb";
    public FileSudokuBoardDao(final String fn) {
        this.fileName = fn.substring(0, fn.indexOf('.')) + EXT;
    }
    public SudokuBoard read() throws CPFileException {
        try (FileInputStream fis = new FileInputStream(fileName); 
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            String message = (e instanceof ClassNotFoundException) ? 
                    CPFileException.CORRUPTED_FILE : CPFileException.READ_ERR;
            throw new CPFileException(message, e);
        }
    }

    public void write(final SudokuBoard obj) throws CPFileException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(obj);
        } catch (IOException e) {
            throw new CPFileException(CPFileException.WRITE_ERR, e);
        }
    }


}

package com.cp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.cp.elems.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;
    public FileSudokuBoardDao(final String fn) {
        this.fileName = fn;
    }
    public SudokuBoard read() {
        try (FileInputStream fis = new FileInputStream(fileName); 
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(final SudokuBoard obj) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

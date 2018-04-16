package com.cp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.cp.elems.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    public SudokuBoard read() {
        try (FileInputStream fis = new FileInputStream("output.dat"); 
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            return (SudokuBoard)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(SudokuBoard obj) {
        try (FileOutputStream fos = new FileOutputStream("output.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

}

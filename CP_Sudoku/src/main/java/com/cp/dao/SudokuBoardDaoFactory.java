package com.cp.dao;

import com.cp.elems.SudokuBoard;
/**
 * 
 * @author sergi
 *
 */
public class SudokuBoardDaoFactory {
    private Dao<SudokuBoard> dao;
    public static Dao<SudokuBoard> getFileDao (String fileName) {
        
        return new FileSudokuBoardDao(fileName);
    }
    
    public static SudokuBoardDaoFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private static class SingletonHolder {
        private static final SudokuBoardDaoFactory INSTANCE = new SudokuBoardDaoFactory();
    }
}

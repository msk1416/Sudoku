package application.play;

import java.util.ArrayList;
import java.util.Random;

import com.cp.Cell;
import com.cp.elems.*;
import com.cp.solver.BacktrackingSudokuSolver;

public class Game {
    private String difficulty;
    //easy --> 9 cells
    //medium --> 6
    //hard --> 4
    private SudokuBoard solution;
    private SudokuBoard gameBoard;
    
    public Game(String diff) {
        this.difficulty = diff;
        gameBoard = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solution = solver.fillBoard();
        if (solution.isResolved()) {
            setInitialBoard();
            gameBoard.print();
        }
        
    }
    
    public boolean isBoardCorrectlyFilled() {
        return gameBoard.boardEqual(solution);
    }
    
    public boolean isBoardEntered() {
        return !gameBoard.existEmptyCells();
    }
    
    public int get(int i, int j) {
        return gameBoard.get(i, j);
    }
    
    public boolean setValue(int i, int j, int value) {
        if (value > 0 && value <= 9) {
            gameBoard.set(i, j, value);
            return true;
        }
        return false;
    }
    
    private void setInitialBoard() {
        int initialCells = 0;
        if (difficulty.equals("Easy")) {
            initialCells = 9;
        } else if (difficulty.equals("Medium")) {
            initialCells = 6;
        } else if (difficulty.equals("Hard")) {
            initialCells = 4;
        } else {
            return;
        }
        ArrayList randomInitialCells = new ArrayList<>(initialCells);
        for (int i = 0; i < initialCells; i++) {
            Random rand = new Random();
            int rr, rc;
            rr = rand.nextInt(8);
            rc = rand.nextInt(8);
            if (!randomInitialCells.contains(new Cell(rr, rc))) {
                gameBoard.set(rr, rc, solution.get(rr, rc));
                randomInitialCells.add(new Cell(rr, rc));
            } else {
                i--;
            }
            
        }
        
    }
    
    
}

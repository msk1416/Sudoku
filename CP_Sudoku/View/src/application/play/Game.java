package application.play;

import java.util.ArrayList;
import java.util.Random;

import com.cp.Cell;
import com.cp.elems.SudokuBoard;
import com.cp.solver.BacktrackingSudokuSolver;

import application.Main;

public class Game {
    private String difficulty;
    
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    //easy --> 9 cells
    //medium --> 6
    //hard --> 4
    private SudokuBoard solution;
    private SudokuBoard gameBoard;
    
    public SudokuBoard getGameBoard() {
        return gameBoard;
    }

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
    
    public Game(SudokuBoard board) {
        this.difficulty = ";;;";
        this.gameBoard = board;
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        this.solution = solver.fillBoard();
        if (this.solution.isResolved()) {
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
        if (difficulty.equals(Main.labels.getString("difficulty.easy"))) {
            initialCells = 9;
        } else if (difficulty.equals(Main.labels.getString("difficulty.medium"))) {
            initialCells = 6;
        } else if (difficulty.equals(Main.labels.getString("difficulty.hard"))) {
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
    
    public void loadSolution() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameBoard.set(i, j, solution.get(i, j));
            }
        }
        
    }
    
    
}

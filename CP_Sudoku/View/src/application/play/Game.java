package application.play;

import com.cp.elems.*;
import com.cp.solver.BacktrackingSudokuSolver;

public class Game {
    private String difficulty;
    //easy --> 9 cells
    //medium --> 6
    //hard --> 4
    private SudokuBoard solution;
    
    public Game(String diff) {
        this.difficulty = diff;
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solution = solver.fillBoard();
        if (solution.isResolved()) {
            
        }
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
        for (int i = 0; i < initialCells; i++)
    }
    
    
}

package application.play;

import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.cp.elems.SudokuBoard;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
public class GameController {

    @FXML
    Label lblTitle;
    @FXML
    Button backBtn;
    @FXML
    GridPane boardGrid;
    @FXML
    Label lblError;
    private String errorEmptyCells = "There are empty cells in the board.";
    private String errorRuleViolations = "The numbers entered cause rule violations.";
    private String msgGameCompleted = "Congratulations. You completed this sudoku.";
    private Game currentGame;
    private String difficulty;
    
    public void init(Game game, String difficulty) {
        lblTitle.setText(lblTitle.getText().replaceFirst("-DIFF-", difficulty));
        this.difficulty = difficulty;
        this.currentGame = game;
        initBoard();
    }
    
    @SuppressWarnings("unchecked")
    private void initBoard() {
        int SIZE = 9;
        int length = SIZE;
        int width = SIZE;
   

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){
                // Create a new TextField in each Iteration
                final TextField tf = new TextField() {
                    @Override
                    public void paste() {} 
                };
                tf.setStyle("-fx-font-weight: bold");
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                int number = this.currentGame.get(x, y);
                if (number != 0) {
                    tf.setText(String.valueOf(number));
                }
                final int copy_x = x;
                final int copy_y = y;
                tf.setEditable(number != 0 ? false : true);
                tf.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler() {

                    @Override
                    public void handle(Event event) {
                        char in = ((KeyEvent)event).getCharacter().charAt(0);
                        if (!Character.isDigit(in) || tf.getText().length() > 0) {
                            event.consume();
                        } else {
                            currentGame.setValue(copy_x, copy_y, Character.getNumericValue(in));
                            if (in == '0') {
                                event.consume();
                            }
                        }
                    }
                    
                });

                // Iterate the Index using the loops
                /*boardGrid.setRowIndex(tf,y);
                boardGrid.setColumnIndex(tf,x);    
                boardGrid.getChildren().add(tf);*/
                boardGrid.add(tf, y, x);
            }
        }
    }

    
    public void goBackToMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Main.fxml"));
        AnchorPane root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            Stage stage = (Stage) lblTitle.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void checkGameFinished() {
        if (!currentGame.isBoardEntered()) {
            lblError.setText(errorEmptyCells);
            lblError.setVisible(true);
        } else if (!currentGame.isBoardCorrectlyFilled()) {
            lblError.setText(errorRuleViolations);
            lblError.setVisible(true);
        } else {
            lblError.setText(msgGameCompleted);
            lblError.setStyle("-fx-text-fill: green");
            lblError.setVisible(true);
        }
    }
    
}

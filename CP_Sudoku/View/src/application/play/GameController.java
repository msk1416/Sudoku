package application.play;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.cp.elems.SudokuBoard;

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

public class GameController {

    @FXML
    Label lblTitle;
    @FXML
    Button backBtn;
    @FXML
    GridPane boardGrid;
    private Game currentGame;
    private String difficulty;
    
    public void init(Game game, String difficulty) {
        lblTitle.setText(lblTitle.getText().replaceFirst("-DIFF-", difficulty));
        this.difficulty = difficulty;
        this.currentGame = game;
        initBoard();
    }
    
    private void initBoard() {
        int SIZE = 9;
        int length = SIZE;
        int width = SIZE;
   

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){

                Random rand = new Random();
                int rand1 = rand.nextInt(2);

                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);
                tf.setText("(" + rand1 + ")");

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
}

package application.play;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.cp.elems.SudokuBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameController {

    @FXML
    Label lblTitle;
    @FXML
    Button backBtn;
    
    private Game currentGame;
    private String difficulty;
    
    public void init(Game game, String difficulty) {
        lblTitle.setText(lblTitle.getText().replaceFirst("-DIFF-", difficulty));
        this.difficulty = difficulty;
        this.currentGame = game;
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

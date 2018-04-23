package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.play.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MainController implements Initializable {
    @FXML
    ComboBox difficultyBox;
    @FXML
    Button startBtn;
    
    ObservableList<String> diffs = FXCollections.observableArrayList(
            "Easy",
            "Medium",
            "Hard"
        );


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        difficultyBox.setItems(diffs);
    }
    
    @FXML
    void loadGame() {
        difficultyBox.getValue();
        Game game = new Game(String.valueOf(difficultyBox.getValue()));
    }
}

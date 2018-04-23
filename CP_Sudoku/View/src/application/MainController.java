package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.play.Game;
import application.play.GameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
    @FXML
    ComboBox difficultyBox;
    @FXML
    Button startBtn;
    @FXML
    Label lblSelectDiff;
    
    ObservableList<String> diffs = FXCollections.observableArrayList(
            "Easy",
            "Medium",
            "Hard"
        );


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficultyBox.setItems(diffs);
    }
    
    @FXML
    void loadGame() {
        if (difficultyBox.getValue() == null) {
            lblSelectDiff.setVisible(true);
        } else {
            lblSelectDiff.setVisible(false);
            difficultyBox.getValue();
            Game game = new Game(String.valueOf(difficultyBox.getValue()));
            //change to game window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/play/GameScene.fxml"));
            AnchorPane root;
            try {
                root = loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                Stage stage = (Stage) difficultyBox.getScene().getWindow();
                stage.setScene(scene);
                GameController gameController = loader.getController();
                gameController.init(game, String.valueOf(difficultyBox.getValue()));
                stage.show();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}

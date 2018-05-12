package application;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
    Label lblWelcome;
    @FXML
    ComboBox difficultyBox;
    @FXML
    Button startBtn;
    @FXML
    Label lblSelectDiff;
    @FXML
    ComboBox langBox;
    Locale currentLocale;
    ResourceBundle labels;
    ObservableList<String> diffs = FXCollections.observableArrayList();


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        langBox.setItems(Main.LANGS);
        langBox.setPromptText(langBox.getItems().get(0).toString());
        currentLocale = getCurrentLocale();
        labels = ResourceBundle.getBundle("SudokuBundle", currentLocale);
        changeLang();
        /*lblSelectDiff.setText(labels.getString("main.error.difficulty"));
        lblWelcome.setText(labels.getString("welcome.title"));
        difficultyBox.setPromptText(labels.getString("select.difficulty"));
        startBtn.setText(labels.getString("start"));*/
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
    
    @FXML
    void changeLang() {
        //TODO: reset stage
        currentLocale = getCurrentLocale();
        labels = ResourceBundle.getBundle("SudokuBundle", currentLocale);
        lblSelectDiff.setText(labels.getString("main.error.difficulty"));
        lblWelcome.setText(labels.getString("welcome.title"));
        difficultyBox.setPromptText(labels.getString("select.difficulty"));
        startBtn.setText(labels.getString("start"));
        setDifficulties();
        difficultyBox.setItems(diffs);
    }
    
    private Locale getCurrentLocale() {
        if (langBox.getValue() != null) {
            if (langBox.getValue().toString().contains("English"))
                return Locale.ENGLISH;
            else return new Locale (langBox.getValue().toString());
        } else return Locale.ENGLISH;
    }
    
    private void setDifficulties() {
        diffs.removeAll(diffs);
        diffs.add(labels.getString("difficulty.easy"));
        diffs.add(labels.getString("difficulty.medium"));
        diffs.add(labels.getString("difficulty.hard"));
    }
}

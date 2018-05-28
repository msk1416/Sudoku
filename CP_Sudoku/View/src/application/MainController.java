package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.cp.dao.SudokuBoardDaoFactory;
import com.cp.elems.SudokuBoard;
import com.cp.exception.CPFileException;

import application.play.Game;
import application.play.GameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
	
    final static Logger logger = Logger.getLogger(MainController.class);
	
	
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
    @FXML
    Button loadBtn;
    @FXML
    Label lblOr;
    @FXML
    Label lblCompleted;
    final FileChooser fileChooser = new FileChooser();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        langBox.setItems(Main.LANGS);
        setBoxLanguage();
        Main.currentLocale = getCurrentLocale();
        Main.labels = ResourceBundle.getBundle("SudokuBundle", Main.currentLocale);
        changeLang();
        logger.debug("Application initialized.");
    }
    
    @FXML
    void loadGame() {
        loadGame(null);
    }
    
    void loadGame(SudokuBoard board) {
        if (difficultyBox.getValue() == null && board == null) {
            lblSelectDiff.setVisible(true);
        } else {
            lblSelectDiff.setVisible(false);
            difficultyBox.getValue();
            Game game;
            
            if (board == null) {
                game = new Game(String.valueOf(difficultyBox.getValue()));
            } else {
                game = new Game(board);
            }
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
        Main.currentLocale = getCurrentLocale();
        Main.labels = ResourceBundle.getBundle("SudokuBundle", Main.currentLocale);
        lblSelectDiff.setText(Main.labels.getString("main.error.difficulty"));
        lblWelcome.setText(Main.labels.getString("welcome.title"));
        difficultyBox.setPromptText(Main.labels.getString("select.difficulty"));
        startBtn.setText(Main.labels.getString("start"));
        setDifficulties();
        difficultyBox.setItems(Main.diffs);
        loadBtn.setText(Main.labels.getString("main.load"));
        lblOr.setText(Main.labels.getString("main.or"));
        lblCompleted.setText(Main.labels.getString("err.completed.game"));
    }

    public Locale getCurrentLocale() {
        if (langBox.getValue() != null) {
            if (langBox.getValue().toString().contains("English"))
                return Locale.ENGLISH;
            else return new Locale (langBox.getValue().toString());
        } else if (Main.currentLocale != null) {
            return Main.currentLocale;            
        } else return Locale.ENGLISH;
        
    }
    
    public static void setDifficulties() {
        Main.diffs.removeAll(Main.diffs);
        Main.diffs.add(Main.labels.getString("difficulty.easy"));
        Main.diffs.add(Main.labels.getString("difficulty.medium"));
        Main.diffs.add(Main.labels.getString("difficulty.hard"));
    }
    
    private void setBoxLanguage() {
        if (Main.currentLocale == null) {
            langBox.setPromptText(Main.LANGS.get(0));
        } else {
            String locale = Main.currentLocale.getLanguage();
            for (int i = 0; i < Main.LANGS.size(); i++) {
                if (Main.LANGS.get(i).toLowerCase().contains(locale)) {
                    langBox.setPromptText(Main.LANGS.get(i));
                    return;
                }
            }
            langBox.setPromptText(Main.LANGS.get(0));
        }
    }
    
    @FXML
    void chooseFileFromDisk() {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(langBox.getScene().getWindow());
        if (file != null) {
            SudokuBoard loadedBoard = null;
			try {
				loadedBoard = SudokuBoardDaoFactory.getFileDao(file.getAbsolutePath()).read();
	            if (!loadedBoard.isResolved())loadGame(loadedBoard);
	            else lblCompleted.setVisible(true);
			} catch (CPFileException e) {
				logger.error(e.getLocalizedMessage());
				return;
			}
        } else {
            logger.debug("null file");
        }
    }
    
    private void configureFileChooser(final FileChooser fileChooser) {      
                fileChooser.setTitle("View Pictures");
                fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
                );                 
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Sudoku Board", "*.cpsb")
                );
        }
    
    @FXML
    void loadInfo() {
        ResourceBundle info = ResourceBundle.getBundle("InfoBundle", Main.currentLocale);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info window");
        alert.setHeaderText("About the author");
        String msg = Main.labels.getString("info.name") + ": " + info.getString("Author") + "\n" +
                   Main.labels.getString("info.from") + ": " + info.getString("Nationality") + "\n" +
                   Main.labels.getString("info.born") + ": " + info.getString("Birth") + "\n" +
                   Main.labels.getString("info.uni.studies") + ": " + info.getString("Studies") + "\n" +
                   Main.labels.getString("info.uni.home") + ": " + info.getString("HomeUni") + "\n" +
                   Main.labels.getString("info.uni.guest") + ": " + info.getString("GuestUni") + "\n" +
                   Main.labels.getString("info.uni.course") + ": " + info.getString("Course");
                   ;
        alert.setContentText(msg);
        alert.showAndWait();

    }
}

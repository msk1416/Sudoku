package application.play;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.cp.dao.SudokuBoardDaoFactory;
import com.cp.exception.CPFileException;

import application.Main;
import application.MainController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class GameController {
    final static Logger logger = Logger.getLogger(GameController.class);
    @FXML
    Label lblTitle;
    @FXML
    Button backBtn;
    @FXML
    GridPane boardGrid;
    @FXML
    Label lblError;
    @FXML
    ComboBox<String> langBox;
    @FXML
    Button checkBtn;
    @FXML
    Button saveBtn;
    @FXML
    Button fillBtn;
    private Game currentGame;
    private String difficulty;
    private boolean loaded = false;

    final FileChooser fileChooser = new FileChooser();
    
    public void init(Game game, String difficulty) {
        langBox.setItems(Main.LANGS);
        setBoxLanguage();
        Main.labels = ResourceBundle.getBundle("SudokuBundle", Main.currentLocale);
        lblTitle.setText(Main.labels.getString("game.difficulty.title").replaceFirst("-DIFF-", difficulty));
        if (game.getDifficulty() != ";;;")
            this.difficulty = difficulty;
        else this.difficulty = game.getDifficulty();
        this.currentGame = game;
        changeLang();
        initBoard();
        loaded = true;
    }
    
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
                tf.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {
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
            logger.error(e.getLocalizedMessage());
            logger.error(e.getStackTrace());
        }
    }
    
    public void checkGameFinished() {
        if (!currentGame.isBoardEntered()) {
            lblError.setText(Main.labels.getString("game.err.empty.cells"));
            lblError.setVisible(true);
        } else if (!currentGame.isBoardCorrectlyFilled()) {
            lblError.setText(Main.labels.getString("game.err.rule.violations"));
            lblError.setVisible(true);
        } else {
            lblError.setText(Main.labels.getString("game.success.msg"));
            lblError.setStyle("-fx-text-fill: green");
            lblError.setVisible(true);
            currentGame.getGameBoard().setResolved(true);
        }
    }
    
    @FXML
    void changeLang() {
        //TODO: reset stage
        if (loaded) Main.currentLocale = getCurrentLocale();
        Main.labels = ResourceBundle.getBundle("SudokuBundle", Main.currentLocale);
        difficulty = translateDifficulty(difficulty);
        lblTitle.setText(Main.labels.getString("game.difficulty.title").replaceFirst("-DIFF-", difficulty));
        backBtn.setText(Main.labels.getString("game.back.menu"));
        checkBtn.setText(Main.labels.getString("game.check"));
        saveBtn.setText(Main.labels.getString("game.save"));
    }
    
    private String translateDifficulty(String diff) {
        int index = Main.diffs.indexOf(diff);
        if (index >= 0) {
            MainController.setDifficulties();
            return Main.diffs.get(index);
        } else 
            return Main.labels.getString("difficulty.custom");
    }
    
    private Locale getCurrentLocale() {
        if (langBox.getValue() != null) {
            if (langBox.getValue().toString().contains("English"))
                return Locale.ENGLISH;
            else return new Locale (langBox.getValue().toString());
        } else return Locale.ENGLISH;
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
    void saveGame() {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Sudoku Board (*.cpsb)", "*.cpsb");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(boardGrid.getScene().getWindow());
        
        
        if (file != null) {
            try {
				SudokuBoardDaoFactory.getFileDao(file.getAbsolutePath()).write(currentGame.getGameBoard());
			} catch (CPFileException e) {
				logger.error(e.getLocalizedMessage());
	            logger.error(e.getStackTrace());
			}
        }
    }
    
    @FXML
    void fillSudokuResult() {
        currentGame.loadSolution();
        checkGameFinished();
        initBoard();
    }
}

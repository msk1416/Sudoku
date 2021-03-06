package application;
	
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import org.apache.log4j.Logger;

public class Main extends Application {
    
    final static Logger logger = Logger.getLogger(Main.class);

    public static ObservableList<String> LANGS = FXCollections.observableArrayList(
            "English",
            "Català"
        );
    public static Locale currentLocale;
    public static ResourceBundle labels;
    public static ObservableList<String> diffs = FXCollections.observableArrayList();
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
    public static Locale getLocale() {
    	return currentLocale;
    }
}

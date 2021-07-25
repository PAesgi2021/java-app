package fr.java.client;

import fr.java.client.utils.FileUtils;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(FileUtils.createSceneFromFXLM(getClass().getResource("/Login.fxml")));
        stage.setTitle("JAVA-APP");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
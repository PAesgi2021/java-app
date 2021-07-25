package fr.java.client;

import fr.java.client.utils.FileUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


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
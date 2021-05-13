package fr.java.client;

import fr.java.client.utils.FileUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/register/Register.fxml"));
        stage.setTitle("JAVA-APP");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
* component  créer un composent FXML  nom + alt entré  crée auto
* scene builder
* 720 x 1280
*
* */
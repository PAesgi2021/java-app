package fr.java.client.components.todolist;

import fr.java.client.components.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class todolistController {

    @FXML
    public HBox listsHBox;


    public void createList(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/createList/createList.fxml"));
        stage.show();
        
    }
}

package fr.java.client.components.createList;

import fr.java.client.services.Instance;
import fr.java.client.services.TodolistService;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class createListController {

    Instance instance = Instance.getInstance();

    @FXML
    TextField titleEntry;
    @FXML
    Button closeButton;

    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }

    public void createListAction() throws IOException {
        this.instance.getTodolistService().addTodolist(this.titleEntry.getText());
        closeBtnAction();

        // getinstance
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/todolist/todolistView.fxml"));
        stage.show();
    }
}

package fr.java.client.components.createList;

import fr.java.client.Service.FileUtils;
import fr.java.client.Service.TodolistService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class createListController {

    TodolistService todolistService = new TodolistService();

    @FXML
    TextField titleEntry;
    @FXML
    Button closeButton;

    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }

    public void createListAction() throws IOException {
        this.todolistService.addTodolist(this.titleEntry.getText());
        closeBtnAction();

        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/todolist/todolistView.fxml"));
        stage.show();
    }
}

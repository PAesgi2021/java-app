package fr.java.client.components.createList;

import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import fr.java.client.services.TodolistService;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class createListController {
    Instance        instance        = Instance.getInstance();
    TodolistService todolistService = TodolistService.getInstance();

    @FXML TextField titleEntry;

    public void createListAction() throws IOException {

        if (this.titleEntry.getText().equals("")) {
            this.titleEntry.setStyle("-fx-border-color: red");
            return;
        }

        Todolist newTodolist = this.todolistService.saveOrUpdateTodolist(new Todolist(this.titleEntry.getText()), this.instance
                .getSpaceService()
                .getCurrentSpace()
                .getId());
        this.instance.getSpaceService().getCurrentSpace().getTodolists().add(newTodolist);
        FileUtils.close(this.titleEntry);
    }

    public void closeWindow() {
        FileUtils.close(this.titleEntry);
    }
}

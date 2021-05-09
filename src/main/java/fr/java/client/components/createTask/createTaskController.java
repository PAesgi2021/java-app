package fr.java.client.components.createTask;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class createTaskController {

    Instance instance = Instance.getInstance();

    @FXML
    TextField titleEntry;
    TextField contentEntry;
    @FXML
    Button closeButton;

    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }

    public void createTaskAction() throws IOException {
        Todolist myTodolist = this.instance.getTodolistService().getCurrentTodolist();
        myTodolist.addTask(new Task("test", "test"));
        closeBtnAction();
    }

}

package fr.java.client.components.createTask;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class createTaskController {

    Instance instance = Instance.getInstance();

    @FXML
    TextField titleEntry;
    @FXML
    TextArea contentEntry;
    @FXML
    Button closeButton;

    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }


    public void createTaskAction(ActionEvent actionEvent) {

       if(!this.titleEntry.getText().equals("") && !this.contentEntry.getText().equals("")){
           this.instance.getTodolistService().getCurrentTodolist().addTask(new Task(this.titleEntry.getText(), this.contentEntry.getText()));
           this.closeBtnAction();
       } else {
           Alert errorAlert = new Alert(Alert.AlertType.ERROR);
           errorAlert.setHeaderText("Error");
           errorAlert.setContentText("you should complete");
           errorAlert.showAndWait();
       }
    }
}



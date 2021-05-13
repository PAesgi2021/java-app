package fr.java.client.components.createTask;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class createTaskController {

    Instance instance = Instance.getInstance();

    @FXML TextField titleEntry;
    @FXML TextArea contentEntry;
    @FXML Button closeButton;
    @FXML Label nbCharContent;
    @FXML DatePicker deadLine;


    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }

    public void createTaskAction(ActionEvent actionEvent) {
        deadLine();
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        boolean res = isSizeContentValid(this.contentEntry.getText());

        if (!this.titleEntry.getText().equals("") && !this.contentEntry.getText().equals("")) {
            if(res){
                this.instance.getTodolistService().getCurrentTodolist().addTask(new Task(this.titleEntry.getText(), this.contentEntry.getText()));
                this.closeBtnAction();
            } else {
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Limited to 1000 char");
                errorAlert.showAndWait();
            }

        } else {
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("you should complete");
            errorAlert.showAndWait();
        }
    }

    public void updateCharContent() {
        this.nbCharContent.setText(this.contentEntry.getLength() + "/" + "1000");
    }


    public boolean isSizeContentValid(String str) {
        return str.length() < 1000;
    }

    public void deadLine() {
        LocalDateTime deadline = this.deadLine.getValue().atStartOfDay();
        System.out.println(deadline);
    }

}



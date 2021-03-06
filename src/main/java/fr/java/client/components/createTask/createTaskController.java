package fr.java.client.components.createTask;

import fr.java.client.entities.Task;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class createTaskController {

    Instance instance = Instance.getInstance();

    @FXML TextField  titleEntry;
    @FXML TextArea   contentEntry;
    @FXML Label      nbCharContent;
    @FXML DatePicker deadLine;


    public void createTaskAction() {

        if (this.titleEntry.getText().equals("") || this.deadLine.getValue() == null) {
            if (this.titleEntry.getText().equals("")) {
                this.titleEntry.setStyle("-fx-border-color: red");
            }
            if (this.deadLine.getValue() == null) {
                this.deadLine.setStyle("-fx-border-color: red");
            }
            return;
        }

        if (!isSizeContentValid(this.contentEntry.getText())) {
            this.contentEntry.setStyle("-fx-border-color: red");
            return;
        }
        if (!isDateValid(this.deadLine.getValue().atStartOfDay())) {
            this.deadLine.setStyle("-fx-border-color: red");
            return;
        }
        Task newTask = this.instance.getSpaceService()
                                    .getTodolistService()
                                    .saveOrUpdateTask(new Task(this.titleEntry.getText(), this.contentEntry.getText(), this.deadLine
                                            .getValue()
                                            .atStartOfDay()), this.instance.getSpaceService()
                                                                           .getTodolistService()
                                                                           .getCurrentTodolist()
                                                                           .getId());
        this.instance.getSpaceService().getTodolistService().getCurrentTodolist().addTask(newTask);
        FileUtils.close(this.titleEntry);
    }

    public void updateCharContent() {
        this.nbCharContent.setText(this.contentEntry.getLength() + "/" + "1000");
    }

    public boolean isSizeContentValid(String str) {
        return str.length() < 1000;
    }

    public boolean isDateValid(LocalDateTime deadLine) {
        return this.deadLine.getValue().atStartOfDay().isAfter(LocalDateTime.now());
    }

    public void closeWindow() {
        FileUtils.close(this.titleEntry);
    }

}



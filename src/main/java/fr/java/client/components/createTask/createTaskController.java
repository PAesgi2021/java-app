package fr.java.client.components.createTask;

import fr.java.client.entities.Task;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.time.LocalDateTime;

public class createTaskController {

    Instance instance = Instance.getInstance();

    @FXML TextField titleEntry;
    @FXML TextArea contentEntry;
    @FXML Label nbCharContent;
    @FXML DatePicker deadLine;
    @FXML Text errorGeneral;
    @FXML Text errorChar;
    @FXML Text errorDate;
    @FXML Pane errorPane;



    public void createTaskAction(ActionEvent actionEvent) {
        this.errorGeneral.setVisible(false);
        this.errorChar.setVisible(false);
        this.errorDate.setVisible(false);
        this.errorPane.setStyle("-fx-border-color: transparant");

        if (this.titleEntry.getText().equals("") || this.deadLine.getValue() == null) {
            this.errorGeneral.setVisible(true);
            this.errorPane.setStyle("-fx-border-color: red");
            if(this.titleEntry.getText().equals("")) {
                this.titleEntry.setStyle("-fx-border-color: red");
            }
            if(this.deadLine.getValue() == null) {
                this.deadLine.setStyle("-fx-border-color: red");
            }
            return;
        }

        if (!isSizeContentValid(this.contentEntry.getText())){
            this.errorChar.setVisible(true);
            this.errorPane.setStyle("-fx-border-color: red");
            this.contentEntry.setStyle("-fx-border-color: red");
            return;
        }
        if(!isDateValid(this.deadLine.getValue().atStartOfDay())){
            this.errorDate.setVisible(true);
            this.errorPane.setStyle("-fx-border-color: red");
            this.deadLine.setStyle("-fx-border-color: red");
            return;
        }

        this.instance.getSpaceService().getTodolistService().getCurrentTodolist().addTask(new Task(this.titleEntry.getText(), this.contentEntry.getText(), this.deadLine.getValue().atStartOfDay()));
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



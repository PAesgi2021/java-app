package fr.java.client.components.taskManager;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskManagerController {

    Instance instance = Instance.getInstance();
    Todolist currentTodolist;
    Task currentTask;

    @FXML TextField titleTask;
    @FXML TextArea contentTask;
    @FXML CheckBox modifiedTaskField;

    @FXML
    protected void initialize() {
        this.currentTodolist = this.instance.getTodolistService().getCurrentTodolist();
        this.currentTask = this.instance.getTodolistService().getCurrentTask();

        this.titleTask.setText(this.currentTask.getTitle());
        this.contentTask.setText(this.currentTask.getContent());
    }

    public void deleteTaskAction() {
        this.currentTodolist.getTasks().remove(this.currentTask);
        this.close();
    }

    public void validTaskAction() {
        //TODO
        this.deleteTaskAction();
    }

    public void checkModifiedField() {
        if (this.modifiedTaskField.isSelected()) {
            this.titleTask.setDisable(false);
            this.contentTask.setDisable(false);
        } else {
            this.titleTask.setDisable(true);
            this.contentTask.setDisable(true);
        }

    }

    public void updateTaskAction() {
        this.currentTask.setTitle(this.titleTask.getText());
        this.currentTask.setContent(this.contentTask.getText());
        this.close();
    }

    public void close() {
        Stage stage = (Stage) this.titleTask.getScene().getWindow();
        stage.close();
    }
}

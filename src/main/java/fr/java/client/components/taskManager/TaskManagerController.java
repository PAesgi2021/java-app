package fr.java.client.components.taskManager;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import fr.java.client.utils.types.TaskStatusType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TaskManagerController {

    Instance instance = Instance.getInstance();
    Todolist currentTodolist;
    Task currentTask;

    @FXML TextField titleTask;
    @FXML TextArea contentTask;
    @FXML CheckBox lockCheckbox;
    @FXML Button saveBtn;
    @FXML Button todoBtn;
    @FXML Button doneBtn;

    @FXML
    protected void initialize() {
        this.currentTodolist = this.instance.getTodolistService().getCurrentTodolist();
        this.currentTask = this.instance.getTodolistService().getCurrentTask();

        this.titleTask.setText(this.currentTask.getTitle());
        this.contentTask.setText(this.currentTask.getContent());

        this.changeStatusDynamically();
        this.doneBtn.setText(LocalDateTime.now().until(this.currentTask.getDeadLine(), ChronoUnit.DAYS) + " days left");
    }

    public void deleteTaskAction() {
        this.currentTodolist.getTasks().remove(this.currentTask);
        this.close();
    }

    public void changeStatusDynamically() {
        if (this.currentTask.getStatus() == TaskStatusType.done) {
            this.todoBtn.setVisible(true);
            this.doneBtn.setVisible(false);
        } else {
            this.todoBtn.setVisible(false);
            this.doneBtn.setVisible(true);
        }
    }

    public void checkFieldAccess() {
        if (this.lockCheckbox.isSelected()) {
            this.titleTask.setDisable(false);
            this.contentTask.setDisable(false);
            this.saveBtn.setVisible(true);
            this.lockCheckbox.setText("LOCK");
        } else {
            this.titleTask.setDisable(true);
            this.contentTask.setDisable(true);
            this.saveBtn.setVisible(false);
            this.lockCheckbox.setText("UNLOCK");
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

    public void todoAction() {
        this.currentTask.setStatus(TaskStatusType.todo);
        this.currentTask.setFinishedDate(null);
        this.changeStatusDynamically();
    }

    public void doneAction() {
        this.currentTask.setStatus(TaskStatusType.done);
        this.currentTask.setFinishedDate(LocalDateTime.now());
        this.todoBtn.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)")));
        this.changeStatusDynamically();
    }
}

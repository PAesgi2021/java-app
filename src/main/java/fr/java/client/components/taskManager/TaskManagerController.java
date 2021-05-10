package fr.java.client.components.taskManager;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import fr.java.client.utils.types.TaskStatusType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
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
    @FXML CheckBox statusCheckbox;

    @FXML
    protected void initialize() {
        this.currentTodolist = this.instance.getTodolistService().getCurrentTodolist();
        this.currentTask = this.instance.getTodolistService().getCurrentTask();

        this.titleTask.setText(this.currentTask.getTitle());
        this.contentTask.setText(this.currentTask.getContent());

        this.reloadStatusCheckbox();
        this.changeDynamicallyStatusCheckbox();
    }

    public void deleteTaskAction() {
        boolean res = FileUtils.confirmationAlert("Delete task", "Are you sure ?");

        if (res) {
            this.currentTodolist.getTasks().remove(this.currentTask);
            this.close();
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

    public void changeDynamicallyStatusCheckbox() {
        if (this.statusCheckbox.isSelected()) {
            this.currentTask.setStatus(TaskStatusType.done);
            this.currentTask.setFinishedDate(LocalDateTime.now());
            this.statusCheckbox.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)")));
            this.statusCheckbox.setStyle("-fx-background-color: #519e20");
        } else {
            this.currentTask.setStatus(TaskStatusType.todo);
            this.currentTask.setFinishedDate(null);
            this.statusCheckbox.setText(LocalDateTime.now().until(this.currentTask.getDeadLine(), ChronoUnit.DAYS) + " days left");
            this.statusCheckbox.setStyle("-fx-background-color: #cd3737");
        }
    }

    public void reloadStatusCheckbox() {
        if (this.currentTask.getStatus() == TaskStatusType.done) {
            this.statusCheckbox.setSelected(true);
        } else {
            this.statusCheckbox.setSelected(false);
        }
    }
}

package fr.java.client.components.taskConfig;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import fr.java.client.utils.types.TaskStatusType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TaskConfigController {

    Instance instance = Instance.getInstance();
    Todolist currentTodolist;
    Task currentTask;

    @FXML TextField titleTask;
    @FXML TextArea descriptionTask;
    @FXML TextFlow titleTaskTextFlow;
    @FXML TextFlow descriptionTaskTextFlow;
    @FXML CheckBox lockCheckbox;
    @FXML Button saveBtn;
    @FXML CheckBox statusCheckbox;
    @FXML Pane headerTaskConfig;
    @FXML Label nbCharDescription;
    @FXML VBox component;
    @FXML Label textComplete;
    @FXML Pane actionPane;


    @FXML
    protected void initialize() {
        this.currentTodolist = this.instance.getSpaceService().getTodolistService().getCurrentTodolist();
        this.currentTask = this.instance.getSpaceService().getTodolistService().getCurrentTask();

        // retrieve data from task to put on the fields
        this.titleTask.setText(this.currentTask.getTitle());
        this.descriptionTask.setText(this.currentTask.getDescription());
        this.updateNbCharDescription();

        Text title = new Text();
        title.setText(this.currentTask.getTitle());
        title.setStyle("-fx-fill: #656565");
        this.titleTaskTextFlow.getChildren().add(title);

        Text description = new Text();
        description.setText(this.currentTask.getDescription());
        description.setStyle("-fx-fill: #666666");
        this.descriptionTaskTextFlow.getChildren().add(description);

        // set some interactive elements
        this.markStatusCheckboxManagement();
        this.updateStatusCheckboxDynamically();

        FileUtils.draggableView(this.component);
    }

    public void deleteBtnAction() {
        boolean res = FileUtils.confirmationAlert("Delete task", "Are you sure ?");

        if (res) {
            this.currentTodolist.getTasks().remove(this.currentTask);
            this.instance.getSpaceService().getTodolistService().deleteTask(this.currentTask);
            FileUtils.close(this.titleTask);
        }
    }

    public void checkFieldAccess() {
        // If 'lockCheckbox' is marked
        //      allow to modify fields
        //      display save btn
        if (this.lockCheckbox.isSelected()) {
            this.titleTask.setVisible(true);
            this.descriptionTask.setVisible(true);
            this.saveBtn.setVisible(true);
            this.titleTaskTextFlow.setVisible(false);
            this.descriptionTaskTextFlow.setVisible(false);
        }
        // else
        //      fields are disable
        //      save btn is hidden
        else {
            this.titleTask.setVisible(false);
            this.descriptionTask.setVisible(false);
            this.saveBtn.setVisible(false);
            this.titleTaskTextFlow.setVisible(true);
            this.descriptionTaskTextFlow.setVisible(true);
        }

    }

    public void saveBtnAction() {
        this.currentTask.setTitle(this.titleTask.getText());
        if (this.isSizeDescriptionValid(this.descriptionTask.getText())) {
            this.currentTask.setDescription(this.descriptionTask.getText());
        }

        System.out.println(this.currentTask.getId() + " TASK ID");
        this.instance.getSpaceService()
                     .getTodolistService()
                     .saveOrUpdateTask(this.currentTask, this.instance.getSpaceService()
                                                                      .getTodolistService()
                                                                      .getCurrentTodolist()
                                                                      .getId());
        FileUtils.close(this.titleTask);
    }

    public void markStatusCheckboxManagement() {
        if (this.currentTask.getStatus() == TaskStatusType.done) {
            this.statusCheckbox.setSelected(true);
        } else {
            this.statusCheckbox.setSelected(false);
        }
    }

    public void updateStatusCheckboxDynamically() {
        // If 'statusCheckbox' is marked
        //      update task: status, finishedDate
        //      change checkbox: text, color
        //      hide lockBtn
        if (this.statusCheckbox.isSelected()) {
            this.currentTask.setStatus(TaskStatusType.done);
            this.currentTask.setFinishedDate(LocalDateTime.now());
            this.statusCheckbox.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)")));
            this.statusCheckbox.setStyle("-fx-background-color: #62b852");
            this.headerTaskConfig.setStyle("-fx-background-color: #62b852");
            this.textComplete.setVisible(false);
            this.actionPane.setVisible(false);
            this.nbCharDescription.setVisible(false);
            this.instance.getSpaceService()
                         .getTodolistService()
                         .saveOrUpdateTask(this.currentTask, this.instance.getSpaceService()
                                                                .getTodolistService()
                                                                .getCurrentTodolist()
                                                                .getId());
        }
        // else
        //      reset task: status, finishedDate
        //      change checkbox: text, color
        //      show lockBtn
        else {
            this.currentTask.setStatus(TaskStatusType.todo);
            this.currentTask.setFinishedDate(null);
            if (this.currentTask.getDeadLine() != null) {
                this.statusCheckbox.setText(LocalDateTime.now().until(this.currentTask.getDeadLine(), ChronoUnit.DAYS) + " days left");
            } else {
                this.statusCheckbox.setText("");
            }
            this.statusCheckbox.setStyle("-fx-background-color: #e26050");
            this.headerTaskConfig.setStyle("-fx-background-color: #e26050");
            this.textComplete.setVisible(true);
            this.actionPane.setVisible(true);
            this.nbCharDescription.setVisible(true);
        }
    }

    public void updateNbCharDescription() {
        this.nbCharDescription.setText(this.descriptionTask.getLength() + "/" + this.instance.getSpaceService().getTodolistService().getCurrentTask().getLimitDescription());
    }

    public boolean isSizeDescriptionValid(String str) {
        return str.length() < this.instance.getSpaceService().getTodolistService().getCurrentTask().getLimitDescription();
    }

}

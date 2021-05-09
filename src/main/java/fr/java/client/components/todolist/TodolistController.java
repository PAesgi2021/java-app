package fr.java.client.components.todolist;

import fr.java.client.components.createTask.createTaskController;
import fr.java.client.services.Instance;
import fr.java.client.services.TodolistService;
import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TodolistController {

    Instance instance = Instance.getInstance();

    @FXML
    public HBox listsHBox;

    @FXML
    protected void initialize() {
        for (Todolist todolist : this.instance.getTodolistService().getTodolists()) {
            addList(todolist);
        }
    }

    public void displayCreateListView() throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/createList/createList.fxml"));
        stage.showAndWait();
        this.refreshAction();
    }

    public void displayCreateTaskView() throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/createTask/createTask.fxml"));
        stage.showAndWait();
        this.refreshAction();
    }

    public void displayTaskManagerView() throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/taskManager/TaskManager.fxml"));
        stage.showAndWait();
        this.refreshAction();
    }

    /**
     * create a list in the listsHBox (TODOLIST)
     * @param todolist
     */
    public void addList(Todolist todolist) {

        Label titleLabel = new Label(todolist.getTitle());
        titleLabel.setStyle("-fx-font-weight: bolder; -fx-text-fill: #4e4b4b; -fx-padding: 10px;");

        Button btnAddTask = new Button();
        btnAddTask.setText("ADD TASK");
        btnAddTask.setCursor(Cursor.HAND);
        btnAddTask.getStyleClass().add("btnActionList");
        btnAddTask.setOnAction(actionEvent -> {
            try {
                this.instance.getTodolistService().setCurrentTodolist(todolist);
                this.displayCreateTaskView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button btnDeleteList = new Button();
        btnDeleteList.setText("DELETE LIST");
        btnDeleteList.setCursor(Cursor.HAND);
        btnDeleteList.getStyleClass().add("btnActionList");
        btnDeleteList.setOnAction(e -> {
            instance.getTodolistService().getTodolists().remove(todolist);
            this.refreshAction();
        });


        StackPane listHeader = new StackPane();
        listHeader.setStyle("-fx-background-color: #d0d0d0");
        listHeader.getChildren().add(titleLabel);
        listHeader.setAlignment(titleLabel, Pos.CENTER_LEFT);
        listHeader.getChildren().add(btnAddTask);
        listHeader.setAlignment(btnAddTask, Pos.CENTER_RIGHT);

        VBox listBody = new VBox();
        listBody.setStyle("-fx-background-color: #d0d0d0; -fx-spacing: 4px; -fx-padding: 5px; -fx-min-width: 210px;");
        addTask(todolist, listBody);

        StackPane listFooter = new StackPane();
        listFooter.setStyle("-fx-background-color: #d0d0d0");
        listFooter.getChildren().add(btnDeleteList);
        listFooter.setAlignment(btnDeleteList, Pos.CENTER_RIGHT);


        VBox list = new VBox();
        list.getChildren().add(listHeader);
        list.getChildren().add(listBody);
        list.getChildren().add(listFooter);
        list.setStyle("-fx-min-width: 225px;");

        this.listsHBox.getChildren().add(list);
    }

    /**
     * create a simple card clickable for each task of a todolist (TASK)
     * @param todolist
     * @param listView
     */
    public void addTask(Todolist todolist, VBox listView) {
        for (Task task : todolist.getTasks()) {

            TextFlow title = new TextFlow();
            title.getStyleClass().add("taskHeader");
            title.getChildren().add(new Text(task.getTitle()));

            TextFlow text = new TextFlow();
            text.getStyleClass().add("taskBody");
            text.getChildren().add(new Text(task.getContent()));

            VBox card = new VBox();

            // remove himself on click
            card.setOnMouseClicked(event -> {
                try {
                    this.instance.getTodolistService().setCurrentTodolist(todolist);
                    this.instance.getTodolistService().setCurrentTask(task);
                    this.displayTaskManagerView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            card.getStyleClass().add("task");
            card.setCursor(Cursor.HAND);
            card.getChildren().add(title);
            card.getChildren().add(text);
            card.setAlignment(Pos.CENTER);


            listView.getChildren().add(card);
        }
    }

    public void refreshAction() {
        this.listsHBox.getChildren().removeAll(this.listsHBox.getChildren());
        this.initialize();
    }

}

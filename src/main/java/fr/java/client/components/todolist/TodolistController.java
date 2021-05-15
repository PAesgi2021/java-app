package fr.java.client.components.todolist;

import fr.java.client.services.Instance;
import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.utils.FileUtils;
import fr.java.client.utils.types.TaskStatusType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TodolistController {
    Instance instance = Instance.getInstance();

    @FXML
    public HBox listsHBox;
    @FXML
    public CheckBox showDoneTasks;
    @FXML
    public VBox component;

    @FXML
    protected void initialize() {
        for (Todolist todolist : this.instance.getSpaceService().getTodolistService().getTodolists()) {
            addList(todolist, TaskStatusType.todo);
        }
    }

    /**
     * create a list of task component
     *
     * @param todolist
     * @param status
     */
    public void addList(Todolist todolist, TaskStatusType status) {

        //Header
        //  + Todolist.title
        //  + If (isTodoList) add task btn
        StackPane listHeader = new StackPane();
        listHeader.getStyleClass().add("todolistCard");

        Label titleLabel = new Label(todolist.getTitle());
        titleLabel.setStyle("-fx-font-weight: bolder; -fx-text-fill: #4e4b4b; -fx-padding: 10px;");
        listHeader.getChildren().add(titleLabel);
        listHeader.setAlignment(titleLabel, Pos.CENTER_LEFT);

        //Body
        //  + cards of task
        VBox listBody = new VBox();
        listBody.getStyleClass().add("todolistCard");
        listBody.getStyleClass().add("todolistCardBody");
        addTask(todolist, listBody, status);

        //Footer
        //  + If (isTodoList) delete list btn
        StackPane listFooter = new StackPane();
        listFooter.getStyleClass().add("todolistCard");

        // todoCard != doneCard
        if (status == TaskStatusType.todo) {
            Button btnAddTask = new Button();
            btnAddTask.setText("ADD TASK");
            btnAddTask.setCursor(Cursor.HAND);
            btnAddTask.getStyleClass().add("btnActionList");
            btnAddTask.setOnAction(actionEvent -> {
                try {
                    this.instance.getSpaceService().getTodolistService().setCurrentTodolist(todolist);
                    this.showCreateTaskView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Button btnDeleteList = new Button();
            btnDeleteList.setText("DELETE LIST");
            btnDeleteList.setCursor(Cursor.HAND);
            btnDeleteList.getStyleClass().add("btnActionList");
            btnDeleteList.setOnAction(e -> {
                instance.getSpaceService().getTodolistService().getTodolists().remove(todolist);
                this.refreshAction();
            });

            listHeader.getChildren().add(btnAddTask);
            listHeader.setAlignment(btnAddTask, Pos.CENTER_RIGHT);
            listFooter.getChildren().add(btnDeleteList);
            listFooter.setAlignment(btnDeleteList, Pos.CENTER_RIGHT);
        }

        // final component
        VBox list = new VBox();
        list.setStyle("-fx-min-width: 225px;");
        list.getChildren().add(listHeader);
        list.getChildren().add(listBody);
        list.getChildren().add(listFooter);
        this.listsHBox.getChildren().add(list);
    }

    /**
     * foreach Task create a clickable card
     *
     * @param todolist
     * @param listView
     * @param status
     */
    public void addTask(Todolist todolist, VBox listView, TaskStatusType status) {
        for (Task task : todolist.getTasks()) {

            // separate todoTask and doneTask
            if (task.getStatus() != status) {
                continue;
            }

            //Header
            //  + banner
            //      If (isTodoTask) red color
            //      If (isDoneTask) green color + Task.finishedDate
            VBox header = new VBox();

            StackPane banner = new StackPane();
            if (status == TaskStatusType.todo) {
                banner.getStyleClass().add("todoTaskBanner");
            } else {
                banner.getStyleClass().add("doneTaskBanner");
                banner.setStyle("-fx-padding: 5px");
                try {
                    banner.getChildren().add(this.createTaskDoneHeader(task, banner));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            header.getChildren().add(banner);

            //Body
            //  + Task.title
            //  + Task.content
            VBox body = new VBox();

            TextFlow title = new TextFlow();
            title.getStyleClass().add("taskHeader");
            title.getChildren().add(new Text(task.getTitle()));
            body.getChildren().add(title);

            if (task.getDescription() != null && task.getDescription().length() > 0 && task.getDescription().length() < 200) {
                TextFlow text = new TextFlow();
                text.getStyleClass().add("taskBody");
                text.getChildren().add(new Text(task.getDescription()));
                body.getChildren().add(text);
            } else if (task.getDescription() != null && task.getDescription().length() > 200) {
                TextFlow text = new TextFlow();
                text.getStyleClass().add("taskBody");
                Text foo = new Text(" description ... ");
                foo.setStyle("-fx-font-size: 10px; -fx-fill: #5eb6d4");
                text.getChildren().add(foo);
                body.getChildren().add(text);
            }

            // final component
            VBox card = new VBox();
            card.getStyleClass().add("taskCard");
            card.setCursor(Cursor.HAND);
            card.getChildren().addAll(List.of(header, body));

            //Card should be clickable
            //  + update currentTodolist, currentTask
            //  + show TaskConfigView
            card.setOnMouseClicked(event -> {
                try {
                    this.instance.getSpaceService().getTodolistService().setCurrentTodolist(todolist);
                    this.instance.getSpaceService().getTodolistService().setCurrentTask(task);
                    this.showTaskConfigView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            listView.getChildren().add(card);
        }
    }

    public void refreshAction() {
        this.listsHBox.getChildren().removeAll(this.listsHBox.getChildren());
        if (this.showDoneTasks.isSelected()) {
            for (Todolist todolist : this.instance.getSpaceService().getTodolistService().getTodolists()) {
                addList(todolist, TaskStatusType.done);
            }
        } else {
            this.initialize();
        }
    }

    public void showLoginView(ActionEvent actionEvent) {
        instance.getUserService().logout();
        Stage stage = new Stage();
        try {
            stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/login/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileUtils.close(this.listsHBox);
        stage.show();
    }

    public void showCreateListView() throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/createList/createList.fxml"));
        FileUtils.closeWhenLoseFocus(stage);
        stage.showAndWait();
        this.refreshAction();
    }

    public void showCreateTaskView() throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/createTask/createTask.fxml"));
        FileUtils.closeWhenLoseFocus(stage);
        stage.showAndWait();
        this.refreshAction();
    }

    public void showTaskConfigView() throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/taskConfig/TaskConfig.fxml"));
        FileUtils.closeWhenLoseFocus(stage);
        stage.initStyle(StageStyle.UNDECORATED);
        this.component.setOpacity(0.8);
        stage.showAndWait();
        this.component.setOpacity(1);
        this.refreshAction();
    }

    public void showProfileAccessView() throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/profile/Profile.fxml"));
        FileUtils.close(this.component);
        stage.show();
    }

    public HBox createTaskDoneHeader(Task task, StackPane banner) throws MalformedURLException {
        Text finishedDate = new Text();
        finishedDate.setStyle("-fx-fill: white");
        finishedDate.setText(task.getFinishedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        StackPane.setAlignment(finishedDate, Pos.CENTER_LEFT);

        HBox result = new HBox();
        result.setSpacing(5);
        URL url = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/validation.png");
        result.getChildren().addAll(List.of(FileUtils.createViewImg(url, 15, 15), finishedDate));
        return result;
    }
}

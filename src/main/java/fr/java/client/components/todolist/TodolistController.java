package fr.java.client.components.todolist;

import fr.java.client.services.Instance;
import fr.java.client.services.TodolistService;
import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

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

    public void addList(Todolist todolist) {
        Label titleLabel = new Label(todolist.getTitle());
        titleLabel.setStyle("-fx-font-weight: bolder; -fx-text-fill: #4e4b4b; -fx-padding: 10px;");

        Pane listHeader = new Pane();
        listHeader.setStyle("-fx-background-color: #d0d0d0");
        listHeader.getChildren().add(titleLabel);

        VBox listBody = new VBox();
        listBody.setStyle("-fx-background-color: #d0d0d0; -fx-spacing: 4px; -fx-padding: 5px; -fx-min-width: 210px;");
        addTask(todolist, listBody);


        Pane listFooter = new Pane();
        listFooter.setStyle("-fx-background-color: #d0d0d0");

        VBox list = new VBox();
        list.getChildren().add(listHeader);
        list.getChildren().add(listBody);
        list.getChildren().add(listFooter);
        list.setStyle("-fx-min-width: 225px;");

        this.listsHBox.getChildren().add(list);
    }

    public void addTask(Todolist todolist, VBox listView) {
        for (Task task : todolist.getTasks()) {
            TextFlow text = new TextFlow();
            text.setStyle("-fx-padding: 10px; -fx-background-color: white;");
            text.getChildren().add(new Text(task.getContent()));
            listView.getChildren().add(text);
        }
    }

    public void refreshAction() {
        this.listsHBox.getChildren().removeAll(this.listsHBox.getChildren());
        this.initialize();
    }
}

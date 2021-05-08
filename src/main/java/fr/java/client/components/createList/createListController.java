package fr.java.client.components.createList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class createListController {

    @FXML
    public HBox listsHBox;

    public void createList(ActionEvent actionEvent) {

        Label title = new Label("Title");
        GridPane listHeader = new GridPane();
        listHeader.getChildren().add(title);

        ListView listBody = new ListView();
        listBody.setId("listNumberX");

        VBox list = new VBox();
        list.getChildren().add(listHeader);
        list.getChildren().add(listBody);

        this.listsHBox.getChildren().add(list);

    }

}

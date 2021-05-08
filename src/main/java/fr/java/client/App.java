package fr.java.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private Parent createContent() {
        ListView listView_todo = new ListView();
        ListView listView_done = new ListView();

        VBox vBox_todo = new VBox(listView_todo);
        VBox vBox_done = new VBox(listView_done);

        HBox hBox_list = new HBox(vBox_todo, vBox_done);

        Button button_create = new Button();
        button_create.setText("create");
        button_create.setOnAction(event -> {
            listView_todo.getItems().add("Item");
        });

        Button button_done = new Button();
        button_done.setText("done");
        button_done.setOnAction(event -> {
            if (listView_todo.getItems().size() > 0)
                listView_todo.getItems().remove(0);
            listView_done.getItems().add("Item");
        });

        HBox hBox_buttons = new HBox(button_create, button_done);


        return new VBox(hBox_list, hBox_buttons);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 1000, 500, Color.GRAY));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
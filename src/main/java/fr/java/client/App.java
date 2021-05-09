package fr.java.client;

import fr.java.client.utils.FileUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        //        stage.setScene(new Scene(createContent(), 1000, 500, Color.GRAY));

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/login/LoginView.fxml"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
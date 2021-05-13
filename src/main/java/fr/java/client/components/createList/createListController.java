package fr.java.client.components.createList;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class createListController {

    Instance instance = Instance.getInstance();

    @FXML
    TextField titleEntry;
    @FXML
    Button    closeButton;

    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }

    public void createListAction() throws IOException {

        if (!this.titleEntry.getText().equals("")) {
                if(FileUtils.confirmationAlert("Add list", "Are you sure ?")) {
                    this.instance.getTodolistService().addTodolist(this.titleEntry.getText());
                    FileUtils.close(this.titleEntry);
                }
        } else {
            FileUtils.showAlert("NAME ERROR", "You should enter a list name", Alert.AlertType.ERROR);
            this.titleEntry.setStyle("-fx-border-color: red");
        }
    }
}

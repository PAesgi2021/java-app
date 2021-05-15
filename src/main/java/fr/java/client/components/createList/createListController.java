package fr.java.client.components.createList;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class createListController {
    Instance instance = Instance.getInstance();

    @FXML TextField titleEntry;
    @FXML Pane errorPane;
    @FXML Text errorTitle;

    public void createListAction() throws IOException {

        this.errorTitle.setVisible(false);
        this.errorPane.setStyle("-fx-border-color: transparant");

        if (this.titleEntry.getText().equals("")) {
            this.errorTitle.setVisible(true);
            this.errorPane.setStyle("-fx-border-color: red");
            this.titleEntry.setStyle("-fx-border-color: red");
        }
    }

    public void closeWindow() {
        FileUtils.close(this.titleEntry);
    }
}

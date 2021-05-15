package fr.java.client.components.createList;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.IOException;

public class createListController {
    Instance instance = Instance.getInstance();

    @FXML TextField titleEntry;
    @FXML Pane errorPane;
    @FXML Text errorTitle;

    public void createListAction() throws IOException {

        this.errorTitle.setVisible(false);
        this.errorPane.setStyle("-fx-background-color: transparant");

        if (this.titleEntry.getText().equals("")) {
            this.errorTitle.setVisible(true);
            this.errorPane.setStyle("-fx-background-color: white");
            this.titleEntry.setStyle("-fx-border-color: red");
            return;
        }

        this.instance.getSpaceService().getTodolistService().addTodolist(this.titleEntry.getText());
        FileUtils.close(this.titleEntry);
    }

    public void closeWindow() {
        FileUtils.close(this.titleEntry);
    }
}

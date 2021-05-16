package fr.java.client.components.createList;

import fr.java.client.entities.Todolist;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.io.IOException;

public class createListController {
    Instance instance = Instance.getInstance();

    @FXML TextField titleEntry;

    public void createListAction() throws IOException {

        if (this.titleEntry.getText().equals("")) {
            this.titleEntry.setStyle("-fx-border-color: red");
            return;
        }

        this.instance.getSpaceService().getCurrentSpace().getTodolists().add(new Todolist(this.titleEntry.getText()));
        FileUtils.close(this.titleEntry);
    }

    public void closeWindow() {
        FileUtils.close(this.titleEntry);
    }
}

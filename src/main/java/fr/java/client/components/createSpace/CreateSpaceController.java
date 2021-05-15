package fr.java.client.components.createSpace;

import fr.java.client.entities.Space;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateSpaceController {
    Instance instance = Instance.getInstance();

    @FXML Button homeBtn;
    @FXML MenuButton settingsMenu;
    @FXML MenuItem profileMenu;
    @FXML MenuItem logoutMenu;
    @FXML TextField spaceNameEntry;
    @FXML TextField tagNameEntry;
    @FXML TextArea descriptionEntry;
    @FXML RadioButton privateVisibility;
    @FXML RadioButton publicVisibility;
    @FXML Button cancel;
    @FXML Button createSpace;
    @FXML StackPane errorPane;
    private String spaceVisibility;
    
    @FXML 
    public void initialize() throws MalformedURLException {
        URL url = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/cadenas.png");
        this.privateVisibility.setGraphic(FileUtils.createViewImg(url, 15, 15));
        url = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/globale.png");
        this.publicVisibility.setGraphic(FileUtils.createViewImg(url, 15, 15));
        FileUtils.setUpNavbarImg(this.homeBtn, this.settingsMenu, this.profileMenu, this.logoutMenu);
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void homeAction(ActionEvent actionEvent) {
    }

    public void privateVisibilityAction() {
        this.publicVisibility.setSelected(false);
        this.spaceVisibility = "private";
    }

    public void publicVisibilityAction() {
        this.privateVisibility.setSelected(false);
        this.spaceVisibility = "public";
    }

    public void createSpaceAction() throws IOException {

        if (this.spaceNameEntry.getText().isEmpty()) {
            this.showError("Space name is required");
            this.spaceNameEntry.getStyleClass().add("errorHighlighter");
            return;
        }
        this.spaceNameEntry.getStyleClass().remove("errorHighlighter");

        if (this.tagNameEntry.getText().isEmpty()) {
            this.showError("Space tag is required");
            this.tagNameEntry.getStyleClass().add("errorHighlighter");
            return;
        }
        this.tagNameEntry.getStyleClass().remove("errorHighlighter");

        if (this.spaceVisibility == null) {
            this.showError("Space visibility is required");
            this.publicVisibility.getStyleClass().add("errorHighlighter");
            this.privateVisibility.getStyleClass().add("errorHighlighter");
            return;
        }
        this.publicVisibility.getStyleClass().remove("errorHighlighter");
        this.privateVisibility.getStyleClass().remove("errorHighlighter");

        // after error cases
        // create a new Space and update the currentSpace
        Space newSpace = new Space(
                this.spaceNameEntry.getText(),
                this.spaceVisibility,
                this.tagNameEntry.getText(),
                this.instance.getUserService().getUser()
        );
        this.instance.getSpaceService().setCurrentSpace(newSpace);
        this.instance.getSpaceService().getSpaces().add(newSpace);

        FileUtils.showView(this.cancel,"space/Space.fxml");
    }

    public void showError(String message) {
        this.errorPane.getChildren().clear();
        Label errorText = new Label(message);
        this.errorPane.getChildren().add(errorText);
        this.errorPane.setStyle("-fx-padding: 15; -fx-border-color: #eeeff0");
    }
}

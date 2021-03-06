package fr.java.client.components.createSpace;

import fr.java.client.entities.Space;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateSpaceController {
    Instance  instance  = Instance.getInstance();
    FileUtils fileUtils = new FileUtils();

    @FXML   Button      homeBtn;
    @FXML   MenuButton  settingsMenu;
    @FXML   MenuItem    profileMenu;
    @FXML   MenuItem    logoutMenu;
    @FXML   TextField   spaceNameEntry;
    @FXML   TextField   tagNameEntry;
    @FXML   TextArea    descriptionEntry;
    @FXML   RadioButton privateVisibility;
    @FXML   RadioButton publicVisibility;
    @FXML   Button      cancel;
    @FXML   Button      createSpace;
    @FXML   StackPane   errorPane;
    private String      spaceVisibility;

    @FXML
    public void initialize() throws MalformedURLException {
        URL url = getClass().getResource("/images/cadenas.png");
        this.privateVisibility.setGraphic(FileUtils.createViewImg(url, 15, 15));
        url = getClass().getResource("/images/globale.png");
        this.publicVisibility.setGraphic(FileUtils.createViewImg(url, 15, 15));
        fileUtils.setUpNavbarImg(this.homeBtn, this.settingsMenu, this.profileMenu, this.logoutMenu);
    }

    public void logout() throws IOException {
        fileUtils.logout(this.cancel);
    }

    public void homeAction() throws IOException {
        fileUtils.showView(this.cancel, getClass().getResource("/Space.fxml"));
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

        // refresh errors indications
        this.spaceNameEntry.getStyleClass().remove("errorHighlighter");
        this.tagNameEntry.getStyleClass().remove("errorHighlighter");
        this.publicVisibility.getStyleClass().remove("errorHighlighter");
        this.privateVisibility.getStyleClass().remove("errorHighlighter");

        if (this.spaceNameEntry.getText().isEmpty()) {
            this.showError("Space name is required");
            this.spaceNameEntry.getStyleClass().add("errorHighlighter");
            return;
        }

        if (this.tagNameEntry.getText().isEmpty()) {
            this.showError("Space tag is required");
            this.tagNameEntry.getStyleClass().add("errorHighlighter");
            return;
        }

        if (this.spaceVisibility == null) {
            this.showError("Space visibility is required");
            this.publicVisibility.getStyleClass().add("errorHighlighter");
            this.privateVisibility.getStyleClass().add("errorHighlighter");
            return;
        }

        // after error cases
        // create a new Space and update the currentSpace
        Space newSpace = new Space(
                this.spaceNameEntry.getText(),
                this.spaceVisibility,
                this.tagNameEntry.getText(),
                this.instance.getUserService().getUser(),
                this.descriptionEntry.getText()
        );
        this.instance.getSpaceService().addSpace(newSpace);
        this.instance.getSpaceService().setCurrentSpace(newSpace);

        fileUtils.showView(this.cancel, getClass().getResource("/Space.fxml"));
    }

    public void showError(String message) {
        this.errorPane.getChildren().clear();
        Label errorText = new Label(message);
        this.errorPane.getChildren().add(errorText);
        this.errorPane.setStyle("-fx-padding: 15; -fx-border-color: #eeeff0");
    }

    public void cancelAction() throws IOException {
        fileUtils.showView(this.cancel, getClass().getResource("/Space.fxml"));
    }
}

package fr.java.client.components.space;

import fr.java.client.entities.Space;
import fr.java.client.entities.User;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SpaceController {
    Instance instance = Instance.getInstance();
    List<Space> spaces = this.instance.getSpaceService().getSpaces();
    User user = this.instance.getUserService().getUser();

    @FXML VBox spacesContainer;
    @FXML Label yourNumberSpaces;
    @FXML Label numberFavoriteSpaces;
    @FXML Button newSpace;
    @FXML Button personalSpacesBtn;
    @FXML Button allSpacesBtn;
    @FXML Button yourSpacesBtn;
    @FXML Button favoriteSpacesBtn;
    @FXML Button exploreSpacesBtn;
    @FXML HBox yourSpacesMenuBtnHBox;
    @FXML Separator lastSeparator;
    @FXML Button homeBtn;
    @FXML MenuButton settingsMenu;
    @FXML MenuItem profileMenu;
    @FXML MenuItem logoutMenu;
    @FXML Label numberExploreSpaces;

    @FXML
    public void initialize() throws IOException {
        this.allSpaceAction();
        this.updateBadgesNumber();
        FileUtils.setUpNavbarImg(this.homeBtn, this.settingsMenu, this.profileMenu, this.logoutMenu);
    }

    private void createSpaceCard(Space space) throws IOException {
        this.instance.getSpaceService().setCurrentSpace(space);

        FXMLLoader loader = new FXMLLoader();
        BorderPane mainPane = loader.load(
                new File("src/main/java/fr/java/client/components/spaceCard/SpaceCard.fxml")
                        .toURI()
                        .toURL()
        );
        this.spacesContainer.getChildren().add(mainPane);
    }

    public void allSpaceAction() throws IOException {
        this.highlightOnlySelectedBtn(this.allSpacesBtn, List.of(this.personalSpacesBtn));

        // show only the spaces if the user is a collaborator
        this.spacesContainer.getChildren().clear();
        for (int i = this.spaces.size()-1; i > 0; i--) {
            Space space = this.spaces.get(i);
            if (this.isUserPresent(space, user)) {
                this.createSpaceCard(space);
            }
        }
    }

    public void personalSpaceAction() throws IOException {
        this.highlightOnlySelectedBtn(this.personalSpacesBtn, List.of(this.allSpacesBtn));

        // show only the spaces if the user is the author
        this.spacesContainer.getChildren().clear();
        for (int i = this.spaces.size()-1; i > 0; i--) {
            Space space = this.spaces.get(i);
            if (space.getAuthor() == user) {
                this.createSpaceCard(space);
            }
        }
    }

    public boolean isUserPresent(Space space, User user) {
        for (User element : space.getUsers()) {
            if (element.equals(user)) return true;
        }
        return false;
    }

    public void yourSpacesAction() throws IOException {
        this.highlightOnlySelectedBtn(this.yourSpacesBtn, List.of(this.favoriteSpacesBtn, this.exploreSpacesBtn));
        this.showYourSpaces();
        this.allSpaceAction();
    }

    public void favoriteSpacesAction() {
        this.highlightOnlySelectedBtn(this.favoriteSpacesBtn, List.of(this.yourSpacesBtn, this.exploreSpacesBtn));
        this.hideYourSpaces();
    }

    public void exploreSpacesAction() throws IOException {
        this.highlightOnlySelectedBtn(this.exploreSpacesBtn, List.of(this.yourSpacesBtn, this.favoriteSpacesBtn));
        this.hideYourSpaces();

        // show all existing spaces
        this.spacesContainer.getChildren().clear();
        for (int i = this.spaces.size()-1; i > 0; i--) {
            Space space = this.spaces.get(i);
            this.createSpaceCard(space);
        }
    }

    // highlight only the selected btn
    public void highlightOnlySelectedBtn(Button firstBtn, List<Button> othersBtn) {
        if (!firstBtn.getStyleClass().contains("selectedBtn")) {
            firstBtn.getStyleClass().add("selectedBtn");
        }

        othersBtn.forEach(e -> {
           e.getStyleClass().remove("selectedBtn");
        });
    }

    public void hideYourSpaces() {
        this.lastSeparator.setVisible(false);
        this.yourSpacesMenuBtnHBox.setVisible(false);
    }

    public void showYourSpaces() {
        this.lastSeparator.setVisible(true);
        this.yourSpacesMenuBtnHBox.setVisible(true);
    }

    public void logout() throws IOException {
        FileUtils.showView(this.allSpacesBtn, "login/Login.fxml");
    }

    public void homeAction() throws IOException {
        FileUtils.showView(this.allSpacesBtn, "space/Space.fxml");
    }

    public void newSpaceAction() throws IOException {
        FileUtils.showView(this.newSpace, "createSpace/CreateSpace.fxml");
    }

    public void updateBadgesNumber() {
        int countYourSpaces = 0;
        for (int i = this.spaces.size()-1; i > 0; i--) {
            Space space = this.spaces.get(i);
            if (this.isUserPresent(space, user)) {
                countYourSpaces++;
            }
        }

        this.yourNumberSpaces.setText(countYourSpaces + "");
        this.numberExploreSpaces.setText(this.spaces.size() + "");
    }
}

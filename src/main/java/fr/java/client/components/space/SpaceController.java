package fr.java.client.components.space;

import fr.java.client.entities.Space;
import fr.java.client.entities.User;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import fr.java.client.utils.types.SpaceTab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class SpaceController {
    Instance    instance  = Instance.getInstance();
    List<Space> spaces    = this.instance.getSpaceService().getSpaces();
    User        user      = this.instance.getUserService().getUser();
    FileUtils   fileUtils = new FileUtils();

    @FXML   VBox       spacesContainer;
    @FXML   Label      yourNumberSpaces;
    @FXML   Label      numberFavoriteSpaces;
    @FXML   Button     newSpace;
    @FXML   Button     personalSpacesBtn;
    @FXML   Button     allSpacesBtn;
    @FXML   Button     yourSpacesBtn;
    @FXML   Button     favoriteSpacesBtn;
    @FXML   Button     exploreSpacesBtn;
    @FXML   HBox       yourSpacesMenuBtnHBox;
    @FXML   Separator  lastSeparator;
    @FXML   Button     homeBtn;
    @FXML   MenuButton settingsMenu;
    @FXML   MenuItem   profileMenu;
    @FXML   MenuItem   logoutMenu;
    @FXML   Label      numberExploreSpaces;
    @FXML   TextField  tfFilter;
    @FXML   MenuButton mbSort;
    private MenuItem   selectedFilter;

    @FXML
    public void initialize() throws IOException {
        this.yourSpacesAction();
        this.updateBadgesNumber();
        fileUtils.setUpNavbarImg(this.homeBtn, this.settingsMenu, this.profileMenu, this.logoutMenu);
        tfFilter.setDisable(true);
        filterSpace();

    }

    private void createSpaceCard(Space space) throws IOException {
        this.instance.getSpaceService().setCurrentSpace(space);
        BorderPane mainPane = FXMLLoader.load(
                getClass().getResource("/SpaceCard.fxml")

        );
        this.spacesContainer.getChildren().add(mainPane);
        this.instance.getSpaceService().setCurrentSpace(null);
    }

    public void allSpaceAction() throws IOException {
        this.highlightOnlySelectedBtn(this.allSpacesBtn, List.of(this.personalSpacesBtn));

        // show only the spaces if the user is a collaborator
        this.spacesContainer.getChildren().clear();
        for (int i = this.spaces.size() - 1; i >= 0; i--) {
            Space space = this.spaces.get(i);
            this.createSpaceCard(space);
        }
    }

    public void personalSpaceAction() throws IOException {
        this.highlightOnlySelectedBtn(this.personalSpacesBtn, List.of(this.allSpacesBtn));

        // show only the spaces if the user is the author
        this.spacesContainer.getChildren().clear();
        for (int i = this.spaces.size() - 1; i >= 0; i--) {
            Space space = this.spaces.get(i);
            if (space.getAuthor() == user) {
                this.createSpaceCard(space);
            }
        }
    }

    public boolean isUserPresent(Space space, User user) {
        return space.getAuthor().getId().equals(user.getId());
    }

    public void yourSpacesAction() throws IOException {
        instance.getSpaceService().setSpaceTab(SpaceTab.Personal);
        this.highlightOnlySelectedBtn(this.yourSpacesBtn, List.of(this.favoriteSpacesBtn, this.exploreSpacesBtn));
        this.showYourSpaces();
        this.allSpaceAction();
    }

    public void favoriteSpacesAction() {
        this.highlightOnlySelectedBtn(this.favoriteSpacesBtn, List.of(this.yourSpacesBtn, this.exploreSpacesBtn));
        this.hideYourSpaces();
    }

    public void exploreSpacesAction() throws IOException {
        instance.getSpaceService().setSpaceTab(SpaceTab.All);
        this.highlightOnlySelectedBtn(this.exploreSpacesBtn, List.of(this.yourSpacesBtn, this.favoriteSpacesBtn));
        this.hideYourSpaces();

        // show all existing spaces
        this.spacesContainer.getChildren().clear();
        for (int i = this.spaces.size() - 1; i >= 0; i--) {
            Space space = this.spaces.get(i);
            if (space.getVisibility() == "public") {
                this.createSpaceCard(space);
            }
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
        fileUtils.logout(this.allSpacesBtn);
    }

    public void homeAction() throws IOException {
        // isEmpty
    }

    public void newSpaceAction() throws IOException {
        fileUtils.showView(this.newSpace, getClass().getResource("/CreateSpace.fxml"));
    }

    public void updateBadgesNumber() {
        int countYourSpaces = 0;
        for (int i = this.spaces.size() - 1; i >= 0; i--) {
            Space space = this.spaces.get(i);
            if (this.isUserPresent(space, user)) {
                countYourSpaces++;
            }
        }

        int countExploreSpaces = 0;
        for (int i = this.spaces.size() - 1; i >= 0; i--) {
            Space space = this.spaces.get(i);
            if (space.getVisibility().equals("public")) {
                countExploreSpaces++;
            }
        }

        this.yourNumberSpaces.setText(countYourSpaces + "");
        this.numberExploreSpaces.setText(countExploreSpaces + "");
    }

    public void filterSpace() {
        tfFilter.setOnKeyTyped(event -> {
            if (tfFilter.getText().length() > 0) {
                if (selectedFilter.getText().equals("Spacename")) {
                    this.spacesContainer.getChildren().clear();
                    for (int i = this.spaces.size() - 1; i > 0; i--) {
                        Space space = this.spaces.get(i);
                        // explore
                        if (!isUserPresent(space, user) && instance.getSpaceService()
                                                                   .getSpaceTab() == SpaceTab.Personal) {
                            continue;
                        }
                        // search by namespace
                        if (space.getName().toLowerCase().contains(tfFilter.getText().toLowerCase())) {
                            try {
                                this.createSpaceCard(space);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (selectedFilter.getText().equals("Tag")) {
                    this.spacesContainer.getChildren().clear();
                    for (int i = this.spaces.size() - 1; i > 0; i--) {
                        Space space = this.spaces.get(i);
                        if (!isUserPresent(space, user) && instance.getSpaceService()
                                                                   .getSpaceTab() == SpaceTab.Personal) {
                            continue;
                        }
                        // search by tag
                        if (space.getTag().toLowerCase().contains(tfFilter.getText().toLowerCase())) {
                            try {
                                this.createSpaceCard(space);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } else {
                try {
                    if (instance.getSpaceService().getSpaceTab() == SpaceTab.Personal) {
                        personalSpaceAction();
                    } else if (instance.getSpaceService().getSpaceTab() == SpaceTab.All) {
                        exploreSpacesAction();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void selectedMenuItemAction(ActionEvent actionEvent) {
        tfFilter.setDisable(false);
        selectedFilter = (MenuItem) actionEvent.getSource();
        mbSort.setText(selectedFilter.getText());

    }

    public void profileAction() throws IOException {
        fileUtils.showView(this.allSpacesBtn, getClass().getResource("/Profile.fxml"));
    }
}

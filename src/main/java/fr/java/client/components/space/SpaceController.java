package fr.java.client.components.space;

import fr.java.client.entities.Space;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SpaceController {
    Instance instance = Instance.getInstance();

    @FXML VBox spacesContainer;

    @FXML
    public void initialize() throws IOException {
        List<Space> spaces = this.instance.getSpaceService().getSpaces();

        for (Space space : spaces) {
            this.createSpaceCard(space);
        }
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

    public void logout(ActionEvent actionEvent) {
    }

    public void homeAction(ActionEvent actionEvent) {
    }
}

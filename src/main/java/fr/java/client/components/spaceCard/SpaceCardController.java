package fr.java.client.components.spaceCard;

import fr.java.client.entities.Space;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SpaceCardController {
    Instance instance = Instance.getInstance();

    @FXML Button acronymSpaceBtn;
    @FXML Button spaceBtn;
    @FXML Label usernameLabel;
    @FXML StackPane accessImgPane;
    @FXML Label tagSpace;
    @FXML Label updatedInfoBtn;

    @FXML
    protected void initialize() throws MalformedURLException {
        Space space = this.instance.getSpaceService().getCurrentSpace();
        String firstLetterProjectName = space.getName().substring(0, 1).toUpperCase();

        this.acronymSpaceBtn.setText(firstLetterProjectName);
        this.acronymSpaceBtn.setStyle(randomColor());
        this.spaceBtn.setText(space.getName());
        this.usernameLabel.setText(space.getAuthor().getFirstname().toLowerCase() + space.getAuthor().getLastname().toLowerCase() + " /");
        imgAccessManagement(space);
        this.tagSpace.setText(space.getTag());
        //TODO updatedInfoBtn
    }

    public String randomColor() {
        String blue = "#e3f2fd";
        String grey = "#eeeeee";
        String violet = "#f3e5f5";
        String pink = "#ffebee";

        List<String> colors = List.of(blue, grey, violet, pink);

        int min = 0;
        int max = colors.size() - 1;
        int rand = (int)(Math.random() * ((max - min) + 1)) + min;

        return "-fx-background-color: " + colors.get(rand) + ";";
    }

    public void imgAccessManagement(Space space) throws MalformedURLException {
        if (space.getVisibility().equals("public")) {
            URL url = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/globale.png");
            this.accessImgPane.getChildren().add(FileUtils.createViewImg(url, 15, 15));
        } else {
            URL url = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/cadenas.png");
            this.accessImgPane.getChildren().add(FileUtils.createViewImg(url, 15, 15));
        }
    }
}
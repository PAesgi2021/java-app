package fr.java.client.components.spaceCard;

import fr.java.client.entities.Space;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SpaceCardController {
    Instance  instance  = Instance.getInstance();
    FileUtils fileUtils = new FileUtils();

    @FXML Button    acronymSpaceBtn;
    @FXML Button    spaceBtn;
    @FXML Label     usernameLabel;
    @FXML StackPane accessImgPane;
    @FXML Label     tagSpace;
    @FXML Label     updatedInfoBtn;
    Space space;

    @FXML
    protected void initialize() throws MalformedURLException {
        this.space = this.instance.getSpaceService().getCurrentSpace();

        String firstLetterProjectName = this.space.getName().substring(0, 1).toUpperCase();

        this.acronymSpaceBtn.setText(firstLetterProjectName);
        this.acronymSpaceBtn.setStyle(randomColor());
        this.spaceBtn.setText(this.space.getName());
        this.usernameLabel.setText(this.space.getAuthor().getFirstname().toLowerCase() + space.getAuthor()
                                                                                              .getLastname()
                                                                                              .toLowerCase() + " /");
        imgAccessManagement(this.space);
        this.tagSpace.setText(this.space.getTag());
    }

    public String randomColor() {
        String blue   = "#e3f2fd";
        String grey   = "#eeeeee";
        String violet = "#f3e5f5";
        String pink   = "#ffebee";

        List<String> colors = List.of(blue, grey, violet, pink);

        int min  = 0;
        int max  = colors.size() - 1;
        int rand = (int) (Math.random() * ((max - min) + 1)) + min;

        return "-fx-background-color: " + colors.get(rand) + ";";
    }

    public void imgAccessManagement(Space space) throws MalformedURLException {
        URL url;
        if (space.getVisibility().equals("public")) {
            url = getClass().getResource("/images/globale.png");
        } else {
            url = getClass().getResource("/images/cadenas.png");
        }
        this.accessImgPane.getChildren().add(FileUtils.createViewImg(url, 15, 15));
    }

    public void showTodolistView() throws IOException {
        this.instance.getSpaceService().setCurrentSpace(this.space);
        fileUtils.showView(this.accessImgPane, getClass().getResource("/Todolist.fxml"));
    }

}
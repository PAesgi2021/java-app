package fr.java.client.components.profile;

import fr.java.client.entities.User;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ProfileController {
    
    Instance instance = Instance.getInstance();

    @FXML Label acronymLabel;
    @FXML Label nameLabel;
    @FXML Label usernameLabel;
    @FXML TextField usernameEntry;
    @FXML TextField passwordEntry;
    @FXML TextField firstnameEntry;
    @FXML TextField lastnameEntry;
    @FXML DatePicker dobEntry;
    @FXML TextField roleEntry;
    @FXML Button saveEntry;
    @FXML Button homeBtn;
    @FXML MenuButton settingsMenu;
    @FXML MenuItem logoutMenu;
    @FXML MenuItem profileMenu;
    @FXML Button backBtn;
    private User user;
    
    @FXML
    protected void initialize() throws MalformedURLException {
        this.user = this.instance.getUserService().getUser();
        this.acronymLabel.setText(FileUtils.getAcronymUser());
        this.nameLabel.setText(this.user.getFirstname() + " " + this.user.getLastname());
        this.usernameEntry.setText(this.user.getUsername());
        this.passwordEntry.setText(this.user.getPassword());
        this.firstnameEntry.setText(this.user.getFirstname());
        this.lastnameEntry.setText(this.user.getLastname());
        this.dobEntry.setValue(this.user.getDob().toLocalDate());
        this.roleEntry.setText(this.user.getRoles()+"");
        this.usernameLabel.setText("/ @" + user.getFirstname().toLowerCase() + user.getLastname().toLowerCase());

        FileUtils.setUpNavbarImg(this.homeBtn, this.settingsMenu, this.profileMenu, this.logoutMenu);
        URL url = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/previous.png");
        this.backBtn.setGraphic(FileUtils.createViewImg(url, 15, 15));

    }

    public void saveBtnAction() throws IOException {
        this.instance.getUserService().getUser().setUsername(this.usernameEntry.getText());
        this.instance.getUserService().getUser().setPassword(this.passwordEntry.getText());
        this.instance.getUserService().getUser().setFirstname(this.firstnameEntry.getText());
        this.instance.getUserService().getUser().setLastname(this.lastnameEntry.getText());
        this.instance.getUserService().getUser().setDob(this.dobEntry.getValue().atStartOfDay());

        this.backAction();
    }

    public void homeAction() throws IOException {
        FileUtils.showView(this.usernameEntry, "space/Space.fxml");
    }

    public void logout() throws IOException {
        FileUtils.logout(this.usernameEntry);
    }

    public void backAction() throws IOException {
        if (this.instance.getSpaceService().getCurrentSpace() == null) {
            FileUtils.showView(this.usernameEntry, "space/Space.fxml");
            return;
        }
        FileUtils.showView(this.usernameEntry, "todolist/Todolist.fxml");
    }
}

package fr.java.client.components.profile;

import fr.java.client.entities.User;
import fr.java.client.services.AuthentificationService;
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
    private AuthentificationService authentificationService = AuthentificationService.getInstance();
    FileUtils fileUtils = new FileUtils();

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
        this.roleEntry.setText(this.user.getRoles()+"");
        this.usernameLabel.setText("/ @" + user.getFirstname().toLowerCase() + user.getLastname().toLowerCase());

        fileUtils.setUpNavbarImg(this.homeBtn, this.settingsMenu, this.profileMenu, this.logoutMenu);
        this.backBtn.setGraphic(FileUtils.createViewImg(getClass().getResource("/images/previous.png"), 15, 15));

    }

    public void saveBtnAction() throws IOException {
        this.instance.getUserService().getUser().setUsername(this.usernameEntry.getText());
        this.instance.getUserService().getUser().setPassword(this.passwordEntry.getText());
        this.instance.getUserService().getUser().setFirstname(this.firstnameEntry.getText());
        this.instance.getUserService().getUser().setLastname(this.lastnameEntry.getText());
        this.authentificationService.registerOrUpdateUser(this.instance.getUserService().getUser());

        this.backAction();
    }

    public void homeAction() throws IOException {
        fileUtils.showView(this.usernameEntry, getClass().getResource("/Space.fxml"));
    }

    public void logout() throws IOException {
        fileUtils.logout(this.usernameEntry);
    }

    public void backAction() throws IOException {
        if (this.instance.getSpaceService().getCurrentSpace() == null) {
            fileUtils.showView(this.usernameEntry, getClass().getResource("/Space.fxml"));
            return;
        }
        fileUtils.showView(this.usernameEntry, getClass().getResource("/Todolist.fxml"));
    }
}

package fr.java.client.components.profile;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    @FXML MenuButton menu;
    @FXML MenuItem logoutMenu;
    @FXML MenuItem profileMenu;
    
    @FXML
    protected void initialize() throws MalformedURLException {
        this.acronymLabel.setText(FileUtils.getAcronymUser());
        this.nameLabel.setText(this.instance.getUserService().getUser().getFirstname() + " " + this.instance.getUserService().getUser().getLastname());
        this.usernameEntry.setText(this.instance.getUserService().getUser().getUsername());
        this.passwordEntry.setText(this.instance.getUserService().getUser().getPassword());
        this.firstnameEntry.setText(this.instance.getUserService().getUser().getFirstname());
        this.lastnameEntry.setText(this.instance.getUserService().getUser().getLastname());
        this.dobEntry.setValue(this.instance.getUserService().getUser().getDob());
        this.roleEntry.setText(this.instance.getUserService().getUser().getRoles()+"");

        URL urlHome = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/home.png");
        URL urlAccount = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/account.png");
        URL urlLogout = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/logout.png");
        this.homeBtn.setGraphic(FileUtils.createViewImg(urlHome, 15, 15));
        this.menu.setGraphic(FileUtils.createViewImg(urlAccount, 15, 15));
        this.logoutMenu.setGraphic(FileUtils.createViewImg(urlLogout, 15, 15));
        this.profileMenu.setGraphic(FileUtils.createViewImg(urlAccount, 15, 15));
    }

//    public void checkUpdateAccess() {
//        if(this.updateProfil.isSelected()) {
//            this.usernameEntry.setDisable(false);
//            this.passwordEntry.setDisable(false);
//            this.aliasEntry.setDisable(false);
//            this.ageEntry.setDisable(false);
//            this.saveBtn.setVisible(true);
//        } else {
//            this.usernameEntry.setDisable(true);
//            this.passwordEntry.setDisable(true);
//            this.aliasEntry.setDisable(true);
//            this.ageEntry.setDisable(true);
//            this.saveBtn.setVisible(false);
//        }
//
//    }

    public void saveBtnAction() throws IOException {
        this.instance.getUserService().getUser().setUsername(this.usernameEntry.getText());
        this.instance.getUserService().getUser().setPassword(this.passwordEntry.getText());
        this.instance.getUserService().getUser().setFirstname(this.firstnameEntry.getText());
        this.instance.getUserService().getUser().setLastname(this.lastnameEntry.getText());
        this.instance.getUserService().getUser().setDob(this.dobEntry.getValue());

        FileUtils.showView(this.usernameEntry, "todolist/TodolistView.fxml");
    }

    public void homeAction() throws IOException {
        FileUtils.showView(this.usernameEntry, "todolist/TodolistView.fxml");
    }

    public void logout() throws IOException {
        FileUtils.showView(this.usernameEntry, "login/Login.fxml");
    }
}

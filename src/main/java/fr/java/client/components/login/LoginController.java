package fr.java.client.components.login;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class LoginController {

    //Injection
    Instance instance = Instance.getInstance();

    @FXML TextField tfUsername;
    @FXML TextField tfPassword;

    public void signin(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(tfUsername);
        textFields.add(tfPassword);
        if (FileUtils.validateTextFields(textFields)) {
            if (instance.getUserService().login(tfUsername.getText(), tfPassword.getText())) {
                FileUtils.showAlert("Authentification Succes", "You are now connected !", Alert.AlertType.INFORMATION);
                displayToDoList();
            } else {
                FileUtils.showAlert("Authentification Failed", "Sorry we cannot find username and/or password corresponding.", Alert.AlertType.ERROR);
            }
        }

    }

    public void register(ActionEvent actionEvent) {
        //TODO
    }

    private void displayToDoList() {
        Stage stage = new Stage();
        try {
            stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/todolist/TodolistView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
    }
}

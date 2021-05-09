package fr.java.client.components.login;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class LoginController {

    //Injection
    Instance instance = Instance.getInstance();

    @FXML TextField         tfUsername;
    @FXML TextField         tfPassword;
    @FXML ProgressIndicator pgConnection;


    public void signin(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(tfUsername);
        textFields.add(tfPassword);
        if (FileUtils.validateTextFields(textFields)) {
            pgConnection.setVisible(true);
            if (instance.getUserService().login(tfUsername.getText(), tfPassword.getText())) {
                //Loading de simulation de requete Async vers l'API
                pgConnection.setVisible(false);
                displayToDoList();
            } else {
                pgConnection.setVisible(false);
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

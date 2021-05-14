package fr.java.client.components.login;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    //Injection
    Instance instance = Instance.getInstance();

//    @FXML TextField         tfUsername;
//    @FXML TextField         tfPassword;
//    @FXML ProgressIndicator pgConnection;

    @FXML TextField emailEntry;
    @FXML PasswordField passwordEntry;
    @FXML Button connectionBtn;
    @FXML Button registerBtn;
    @FXML StackPane errorPane;


    public void signIn() throws IOException {

        // use case: wrong login
        if (!instance.getUserService().login(emailEntry.getText(), passwordEntry.getText())) {
            Label errorText = new Label("adresse e-mail et/ou mot de passe incorrects");
            this.errorPane.getChildren().add(errorText);
            this.errorPane.setStyle("-fx-padding: 20; -fx-border-color: #eeeff0");
            return;
        }

        FileUtils.showView(this.connectionBtn, "todolist/TodolistView.fxml");
    }

    public void showRegisterView() throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/register/Register.fxml"));
        FileUtils.close(this.errorPane);
        stage.show();
    }

    public void onEnter() throws IOException {
        this.signIn();
    }
}

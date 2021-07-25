package fr.java.client.components.login;

import fr.java.client.components.space.SpaceAsync;
import fr.java.client.entities.User;
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
    Instance   instance   = Instance.getInstance();
    FileUtils fileUtils = new FileUtils();

    @FXML TextField emailEntry;
    @FXML PasswordField passwordEntry;
    @FXML Button connectionBtn;
    @FXML Button registerBtn;
    @FXML StackPane errorPane;

    @FXML
    protected void initialize() {
        User user = this.instance.getUserService().getUser();
        if (user != null) {
            this.emailEntry.setText(user.getUsername());
        }
    }

    public void signIn() throws IOException {

        // use case: wrong login
        if (!instance.getUserService().login(emailEntry.getText(), passwordEntry.getText())) {
            Label errorText = new Label("adresse e-mail et/ou mot de passe incorrects");
            this.errorPane.getChildren().add(errorText);
            this.errorPane.setStyle("-fx-padding: 20; -fx-border-color: #eeeff0");
            this.emailEntry.setStyle("-fx-border-color: red");
            this.passwordEntry.setStyle("-fx-border-color: red");
        }
        if (instance.getUserService().getUser() != null) {
            fileUtils.showView(this.connectionBtn,getClass().getResource("/Space.fxml"));
        }


    }

    public void showRegisterView() throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM(getClass().getResource("/Register.fxml")));
        FileUtils.close(this.errorPane);
        stage.show();
    }

    public void onEnter() throws IOException {
        this.signIn();
    }
}

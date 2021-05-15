package fr.java.client.components.register;

import fr.java.client.entities.Todolist;
import fr.java.client.entities.User;
import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import fr.java.client.utils.types.TaskStatusType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RegisterController {
    Instance instance = Instance.getInstance();

    @FXML TextField emailEntry;
    @FXML TextField firstnameEntry;
    @FXML TextField lastnameEntry;
    @FXML DatePicker dobEntry;
    @FXML PasswordField passwordEntry;
    @FXML Button redirectLoginBtn;
    @FXML Button registerBtn;
    @FXML StackPane errorPane;

    public void register() throws IOException {

        // use case: wrong e-mail
        if (this.emailEntry.getText().isEmpty()) {
            this.showError("adresse e-mail incorrect");
            return;
        }

        // use case: wrong firstname
        if (this.firstnameEntry.getText().isEmpty()) {
            this.showError("prénom incorrect");
            return;
        }

        // use case: wrong lastname
        if (this.lastnameEntry.getText().isEmpty()) {
            this.showError("nom incorrect");
            return;
        }

        // use case: wrong dob
        if (this.dobEntry.getValue() == null) {
            this.showError("date de naissance incorrect");
            return;
        }

        // use case: wrong password
        if (this.passwordEntry.getText().isEmpty()) {
            this.showError("password incorrect");
            return;
        }

        User registeredUser = new User(
                this.emailEntry.getText(),
                this.passwordEntry.getText(),
                this.firstnameEntry.getText(),
                this.lastnameEntry.getText(),
                this.dobEntry.getValue()
        );

        // create a new user and update current user
        this.instance.getUserService().getAuthentificationService().addUser(registeredUser);
        this.instance.getUserService().setUser(registeredUser);
        FileUtils.showView(this.errorPane, "/login/Login.fxml");
    }

    public void showLoginView() throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/login/Login.fxml"));
        FileUtils.close(this.errorPane);
        stage.show();
    }

    public void showError(String message) {
        this.errorPane.getChildren().clear();
        Label errorText = new Label(message);
        this.errorPane.getChildren().add(errorText);
        this.errorPane.setStyle("-fx-padding: 15; -fx-border-color: #eeeff0");
    }

}
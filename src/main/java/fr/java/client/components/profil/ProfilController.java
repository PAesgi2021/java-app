package fr.java.client.components.profil;

import fr.java.client.services.Instance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfilController {

    Instance instance = Instance.getInstance();

    @FXML
    Text UsernameEntry;
    @FXML
    Text PasswordEntry;
    @FXML
    Text AliasEntry;
    @FXML
    Text AgeEntry;
    @FXML
    Text RoleEntry;

    @FXML
    Button closeButton;

    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }



}

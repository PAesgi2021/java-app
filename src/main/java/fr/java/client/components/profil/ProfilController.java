package fr.java.client.components.profil;

import fr.java.client.entities.Todolist;
import fr.java.client.entities.User;
import fr.java.client.services.Instance;
import fr.java.client.utils.types.Roles;
import fr.java.client.utils.types.TaskStatusType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfilController {

    Instance instance = Instance.getInstance();

    @FXML
    TextField usernameEntry;
    @FXML
    TextField passwordEntry;
    @FXML
    TextField aliasEntry;
    @FXML
    TextField ageEntry;
    @FXML
    TextField roleEntry;

    @FXML
    Button closeButton;

    @FXML
    protected void initialize() {
        usernameEntry.setText(this.instance.getUserService().getUser().getUsername());
        passwordEntry.setText(this.instance.getUserService().getUser().getPassword());
        aliasEntry.setText(this.instance.getUserService().getUser().getAlias());
        ageEntry.setText(this.instance.getUserService().getUser().getAge()+"");
        roleEntry.setText(this.instance.getUserService().getUser().getRoles()+"");
    }

    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }



}

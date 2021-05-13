package fr.java.client.components.profil;

import fr.java.client.services.Instance;
import fr.java.client.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfilController {

    Instance instance = Instance.getInstance();

    @FXML TextField usernameEntry;
    @FXML PasswordField passwordEntry;
    @FXML TextField aliasEntry;
    @FXML TextField ageEntry;
    @FXML TextField roleEntry;
    @FXML Button closeButton;
    @FXML CheckBox updateProfil;
    @FXML Button saveBtn;

    @FXML
    protected void initialize() {
        usernameEntry.setText(this.instance.getUserService().getUser().getUsername());
        passwordEntry.setText(this.instance.getUserService().getUser().getPassword());
        aliasEntry.setText(this.instance.getUserService().getUser().getFirstname());
//        ageEntry.setText(this.instance.getUserService().getUser().getAge()+"");
        roleEntry.setText(this.instance.getUserService().getUser().getRoles()+"");
    }

    public void closeBtnAction() {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }


    public void checkUpdateAccess() {
        if(this.updateProfil.isSelected()) {
            this.usernameEntry.setDisable(false);
            this.passwordEntry.setDisable(false);
            this.aliasEntry.setDisable(false);
            this.ageEntry.setDisable(false);
            this.saveBtn.setVisible(true);
        } else {
            this.usernameEntry.setDisable(true);
            this.passwordEntry.setDisable(true);
            this.aliasEntry.setDisable(true);
            this.ageEntry.setDisable(true);
            this.saveBtn.setVisible(false);
        }

    }

    public void saveBtnAction() {
        this.instance.getUserService().getUser().setUsername(this.usernameEntry.getText());
        this.instance.getUserService().getUser().setPassword(this.passwordEntry.getText());
//        this.instance.getUserService().getUser().setAlias(this.aliasEntry.getText());
//        this.instance.getUserService().getUser().setAge(Integer.parseInt(this.ageEntry.getText()));
        FileUtils.close(this.usernameEntry);
    }
}

package fr.java.client.components.login;

import fr.java.client.services.Instance;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class LoginController {

    //Injection
    Instance instance = Instance.getInstance();

    @FXML TextField tfUsername;
    @FXML TextField tfPassword;


    public void signin(MouseEvent mouseEvent) {
        System.out.println("LOGIN: " + tfUsername.getText() + "PASSWORD: " + tfPassword.getText());

        if (instance.getUserService().login(tfUsername.getText(), tfPassword.getText())) {
            System.out.println("AUTHENTIFICATION REUSSI");
            System.out.println(instance.getUserService().getUser().toString());
        } else {
            System.out.println("ECHEC DE L AUTHENTIFICATION");
        }
    }
}

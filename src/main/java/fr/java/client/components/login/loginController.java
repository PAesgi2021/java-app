package fr.java.client.components.login;

import fr.java.client.service.Instance;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;


public class loginController {

    //Injection
    private Instance instance = Instance.getInstance();

    String username;
    String password;
    @FXML TextField tfUsername;
    @FXML TextField tfPassword;


    public void signin(MouseEvent mouseEvent) {
        this.username = tfUsername.getText();
        this.password = tfPassword.getText();

        System.out.println("LOGIN: " + username + "PASSWORD: " + password);
        instance.login(username, password);

        if (instance.isAuthenticated()) {
        System.out.println("AUTHENTIFICATION REUSSI");
        } else {
            System.out.println("ECHEC DE L AUTHENTIFICATION");
        }

    }
}

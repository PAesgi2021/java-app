package fr.java.client.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FileUtils {

    public static Scene createSceneFromFXLM(String path) throws IOException {
        URL    file = new File(path).toURI().toURL();
        Parent root = FXMLLoader.load(file);
        return new Scene(root);
    }


    public static void showAlert(String header, String body, Alert.AlertType alertType)  {
        Alert errorAlert = new Alert(alertType);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(body);
        errorAlert.showAndWait();
    }

    /**
     * This function verify and turn in red TextField until they are filled.
     * @param textFields
     * @return
     */
    public static boolean validateTextFields(ArrayList<TextField> textFields) {
        int result = 0;
        for (TextField tf : textFields) {
            if (tf.getText().length() > 0) {
               result += 1;
            } else {
                tf.setStyle("-fx-border-color: red");
                tf.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (tf.getText().length() > 0) {
                        tf.setStyle("-fx-border-color: whitesmoke");
                    }
                });
            }
        }
        return result == textFields.size();
    }


}

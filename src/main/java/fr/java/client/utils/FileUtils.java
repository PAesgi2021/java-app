package fr.java.client.utils;

import fr.java.client.services.Instance;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FileUtils {

    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static Scene createSceneFromFXLM(String path) throws IOException {
        URL    file = new File(path).toURI().toURL();
        Parent root = FXMLLoader.load(file);
        return new Scene(root);
    }

    public static void showAlert(String header, String body, Alert.AlertType alertType) {
        Alert errorAlert = new Alert(alertType);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(body);
        errorAlert.initStyle(StageStyle.UTILITY);
        errorAlert.showAndWait();
    }

    public static boolean confirmationAlert(String header, String body)  {
        Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(body);
        errorAlert.showAndWait();

        if(errorAlert.getResult().getText() == "OK"){
            return true;
        }
        return errorAlert.getResult().getText().equals("OK");
    }

    public static void closeWhenLoseFocus(Stage stage) {
        stage.focusedProperty().addListener((ov, onHidden, onShown) -> {
            if(!stage.isFocused())
                Platform.runLater(() -> stage.close());
        });
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

    public static void close(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static void draggableView(Node node) {
        node.setOnMousePressed(pressEvent -> {
            node.setOnMouseDragged(dragEvent -> {
                node.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                node.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }


    public static ImageView createViewImg(URL url, double height, double width) throws MalformedURLException {
        Image img = new Image(url.toString());
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(height);
        imgView.setFitWidth(width);
        return imgView;
    }

    public static String getAcronymUser() {
        Instance instance = Instance.getInstance();
        if (instance.getUserService().getUser().getFirstname() != null && instance.getUserService().getUser().getLastname() != null) {
            String firstLetterFirstname = instance.getUserService().getUser().getFirstname().substring(0, 1).toUpperCase();
            String firstLetterLastname = instance.getUserService().getUser().getLastname().substring(0, 1).toUpperCase();
            return firstLetterFirstname + firstLetterLastname;
        }
        return "XX";
    }

    public static void showView(Node node, String path) throws IOException {
        Stage stage = new Stage();
        stage.setScene(FileUtils.createSceneFromFXLM("src/main/java/fr/java/client/components/" + path));
        FileUtils.close(node);
        stage.show();
    }

    public static void setUpNavbarImg(Button homeBtn, MenuButton settingsMenu, MenuItem profileMenu, MenuItem logoutMenu) throws MalformedURLException {
        URL urlHome = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/home.png");
        URL urlAccount = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/account.png");
        URL urlLogout = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/logout.png");
        URL urlSettings = new URL("file:///" + FileUtils.PROJECT_PATH + "/src/main/resources/images/settings.png");
        homeBtn.setGraphic(FileUtils.createViewImg(urlHome, 15, 15));
        settingsMenu.setGraphic(FileUtils.createViewImg(urlSettings, 15, 15));
        logoutMenu.setGraphic(FileUtils.createViewImg(urlLogout, 15, 15));
        profileMenu.setGraphic(FileUtils.createViewImg(urlAccount, 15, 15));
    }
}
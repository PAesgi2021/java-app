package fr.java.client.Service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtils {

    public static Scene createSceneFromFXLM(String path) throws IOException {
        URL file = new File(path).toURI().toURL();
        Parent root = FXMLLoader.load(file);
        return new Scene(root);
    }
}

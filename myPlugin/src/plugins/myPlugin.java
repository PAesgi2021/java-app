package plugins;

import fr.java.client.IPlugin;
import fr.java.client.services.AsyncService;
import javafx.scene.image.WritableImage;

public class myPlugin implements IPlugin {
    /**
     * Prints out the content of the system property “user.home”.
     */
    public void run() {

        System.out.println(getClass().getName() + ": user.home: " + System.getProperty("user.home"));

    }


}

package plugins;

import fr.java.client.IPlugin;

public class myPlugin implements IPlugin {
    /**
     * Prints out the content of the system property “user.home”.
     */
    public void run() {

        System.out.println(getClass().getName() + ": user.home: " + System.getProperty("user.home"));

    }


}

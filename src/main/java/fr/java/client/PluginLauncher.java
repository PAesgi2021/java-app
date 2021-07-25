package fr.java.client;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLauncher {


    public IPlugin find() {
        File        selectedJarFile = new File("./myPlugin.jar");
        ClassLoader loader  = null;
        try {
            loader = URLClassLoader.newInstance(new URL[]{selectedJarFile.toURI().toURL()});
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        IPlugin selectedPlugin = null;
        try {
            selectedPlugin = (IPlugin) loader.loadClass("plugins.myPlugin")
                                                         .getDeclaredConstructor()
                                                         .newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return selectedPlugin;
    }

}

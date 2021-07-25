package fr.java.client;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLauncher {


    public IPlugin find() {
        File        authorizedJarFile = new File("./myPlugin.jar");
        ClassLoader authorizedLoader  = null;
        try {
            authorizedLoader = URLClassLoader.newInstance(new URL[]{authorizedJarFile.toURI().toURL()});
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        IPlugin authorizedPlugin = null;
        try {
            authorizedPlugin = (IPlugin) authorizedLoader.loadClass("plugins.myPlugin")
                                                         .getDeclaredConstructor()
                                                         .newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return authorizedPlugin;
    }

}

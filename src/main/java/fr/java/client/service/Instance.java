package fr.java.client.service;

import fr.java.client.entities.User;
import fr.java.client.components.todolist.models.Todolist;
import fr.java.client.utils.types.Roles;

import java.util.List;

public class Instance {

    static User user;
    List<Todolist> todolists;
    boolean        isAuthenticated;

    private static Instance instance;

    private Instance() {
        this.isAuthenticated = false;
    }

    public static Instance getInstance() {
        if (Instance.instance == null) {
               return new Instance();
           }
        return instance;
    }

    public void login(String username, String password) {
        // requete http vers l'API qui authentifie l'utilisateur et renvoie les informations du user
        // SessionDTO session = JSONResponseFromApi;
        // this.user = session.user;
        // this.todolists = session.todolists

        //code provisoire pour test en local
        if (username == "root" && password == "root") {
            this.user = new User("root","root","alias",25, Roles.Administrator);
            this.isAuthenticated = true;
        }
    }

    public boolean isAuthenticated() {
        if (isAuthenticated) {
            return true;
        } else {
            return false;
        }
    }

}

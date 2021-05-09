package fr.java.client.services;

import fr.java.client.entities.User;
import fr.java.client.utils.types.Roles;

public class AuthentificationService {

    boolean isAuthenticated;

    private static AuthentificationService authentificationService;

    private AuthentificationService() {
        this.isAuthenticated = false;
    }

    public static AuthentificationService getInstance() {
        if (authentificationService == null) {
            authentificationService = new AuthentificationService();
        }
        return authentificationService;
    }

    public User getAuthentification(String username, String password) throws Exception {
        // requete http vers l'API qui authentifie l'utilisateur et renvoie les informations du user
        // SessionDTO session = JSONResponseFromApi;
        // this.user = session.user;
        // this.todolists = session.todolists

        //code provisoire pour test en local
        if (username.equals("root") && password.equals("root")) {
            this.isAuthenticated = true;
            return new User("root", "root", "toto", 25, Roles.Administrator);
        }
        throw new Exception("Authentification failed.");
    }

    public boolean isAuthenticated() {
        if (isAuthenticated) {
            return true;
        } else {
            return false;
        }
    }
}

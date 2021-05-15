package fr.java.client.services;

import fr.java.client.entities.User;
import fr.java.client.utils.types.Roles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuthentificationService {

    List<User> users;

    boolean isAuthenticated;

    private static AuthentificationService authentificationService;

    private AuthentificationService() {
        this.users = new ArrayList<>();
        this.users.add(new User("root", "root", "root", "root", LocalDate.now()));
        this.users.add(new User("admin", "admin", "admin", "admin", LocalDate.now()));
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
        for (User user : this.users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                this.isAuthenticated = true;
                return user;
            }
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

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}

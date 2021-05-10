package fr.java.client.services;

import fr.java.client.entities.User;
import fr.java.client.utils.types.Roles;

public class UserService {

    private User user;

    private final  AuthentificationService authentificationService;
    private static UserService             userService;

    private UserService() {
        this.authentificationService = AuthentificationService.getInstance();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public boolean login(String username, String password) {
        try {
            this.user = authentificationService.getAuthentification(username, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean logout() {
        try {
            this.user = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUser() {
        return user;
    }


    public AuthentificationService getAuthentificationService() {
        return authentificationService;
    }


}

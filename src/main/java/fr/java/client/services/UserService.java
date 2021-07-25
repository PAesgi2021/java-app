package fr.java.client.services;

import fr.java.client.components.login.dto.LoginDTO;
import fr.java.client.entities.User;

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
            LoginDTO loginDTO = authentificationService.getAuthentification(username, password);
            this.user = new User(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getId(), loginDTO.getFirstname(), loginDTO
                    .getLastname());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean logout() {
        try {
            authentificationService.logoutInstance();
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

    public void setUser(User user) {
        this.user = user;
    }

    public AuthentificationService getAuthentificationService() {
        return authentificationService;
    }


}

package fr.java.client.services;

import fr.java.client.components.login.dto.LoginDTO;
import fr.java.client.components.register.dto.UserDTO;
import fr.java.client.entities.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuthentificationService {

    List<User> users;

    boolean isAuthenticated;

    private static AuthentificationService authentificationService;


    private final String ROUTE_URL = "/er-user";
    private final String LOGIN_URL = "/signin";
    private final String REGISTER_URL = "/signup";

    private final AsyncService asyncService = AsyncService.getInstance();


    private AuthentificationService() {
        this.users = new ArrayList<>();
        this.users.add(new User("root", "root", "root", "root", LocalDateTime.parse("2021-05-16T02:05:14.175702300")));
        this.users.add(new User("admin", "admin", "admin", "admin", LocalDateTime.now()));
        this.isAuthenticated = false;
    }

    public static AuthentificationService getInstance() {
        if (authentificationService == null) {
            authentificationService = new AuthentificationService();
        }
        return authentificationService;
    }

    public LoginDTO getAuthentification(String username, String password) throws Exception {

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);

        LoginDTO response = asyncService.post(ROUTE_URL + LOGIN_URL, LoginDTO.class, loginDTO);

        if (response != null) {
            isAuthenticated = true;
            return response;
        }
        throw new Exception("Authentification failed");
    }

    public boolean isAuthenticated() {
        if (isAuthenticated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean registerUser(User user) {

        UserDTO userToCreate = new UserDTO();
        userToCreate.setUsername(user.getUsername());
        userToCreate.setPassword(user.getPassword());
        userToCreate.setFirstname(user.getFirstname());
        userToCreate.setLastname(user.getLastname());

        try {
            UserDTO response = asyncService.post(ROUTE_URL + REGISTER_URL, UserDTO.class, userToCreate);
            return response != null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void logoutInstance() {
        isAuthenticated = false;
    }
}

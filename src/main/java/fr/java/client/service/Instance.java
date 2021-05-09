package fr.java.client.service;

import fr.java.client.components.todolist.models.Todolist;

import java.util.List;

public class Instance {

    List<Todolist> todolists;


    private final UserService userService;
    private static Instance instance;


    private Instance() {
        this.userService = UserService.getInstance();
    }

    public static Instance getInstance() {
        if (Instance.instance == null) {
            instance = new Instance();
            return instance;
        }
        return instance;
    }


    public UserService getUserService() {
        return userService;
    }
}

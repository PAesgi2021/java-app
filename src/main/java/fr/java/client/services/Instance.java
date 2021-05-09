package fr.java.client.services;

import fr.java.client.entities.Todolist;

import java.util.List;

public class Instance {


    private final UserService userService;
    private final TodolistService todolistService;
    private static Instance instance;


    private Instance() {
        this.userService = UserService.getInstance();
        this.todolistService = TodolistService.getInstance();
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

    public TodolistService getTodolistService() {
        return todolistService;
    }
}

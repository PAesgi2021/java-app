package fr.java.client.services;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;

import java.util.ArrayList;
import java.util.List;

public class TodolistService {

    private List<Todolist> todolists;
    private static TodolistService instance;

    private TodolistService() {
        this.todolists = new ArrayList<>();
        this.addTodolist("Test");
        this.addTodolist("TO-DO");
        this.addTodolist("pouet");
        this.addTodolist("TEST");
        this.addTodolist("test");

        this.todolists.get(0).addTask(new Task("title", "miamiamiamaimaima"));
        this.todolists.get(0).addTask(new Task("title", "miamiamiamaimaima"));
        this.todolists.get(0).addTask(new Task("title", "miamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaima"));
        this.todolists.get(0).addTask(new Task("title", "prouuuuuuuut"));
        this.todolists.get(1).addTask(new Task("title", "prouuuuuuuut"));
        this.todolists.get(4).addTask(new Task("title", "prouuuuuuuut"));
        this.todolists.get(3).addTask(new Task("title", "prouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuut"));

        for (int i = 0; i < 10; i++) {
            this.todolists.get(2).addTask(new Task("title", "prouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuut"));
        }

    }

    public static TodolistService getInstance() {
        if (TodolistService.instance == null) {
            instance = new TodolistService();
            return instance;
        }
        return instance;
    }

    public void addTodolist(String title) {
        this.todolists.add(new Todolist(title));
    }

    public List<Todolist> getTodolists() {
        return todolists;
    }

    public void setTodolists(List<Todolist> todolists) {
        this.todolists = todolists;
    }
}

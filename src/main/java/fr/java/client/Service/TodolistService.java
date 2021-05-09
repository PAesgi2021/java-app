package fr.java.client.Service;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;

import java.util.ArrayList;
import java.util.List;

public class TodolistService {

    private List<Todolist> todolists;

    public TodolistService() {
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
        this.todolists.get(2).addTask(new Task("title", "prouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuut"));
        this.todolists.get(2).addTask(new Task("title", "prouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuutprouuuuuuuut"));
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

package fr.java.client.Service;

import fr.java.client.entities.Todolist;

import java.util.ArrayList;
import java.util.List;

public class TodolistService {

    private List<Todolist> todolists;

    public TodolistService() {
        this.todolists = new ArrayList<>();
        this.addTodolist("test");
        this.addTodolist("test");
        this.addTodolist("test");
        this.addTodolist("test");
        this.addTodolist("test");
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

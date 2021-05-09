package fr.java.client.services;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodolistService {
    private static TodolistService instance;

    private List<Todolist> todolists;
    private Todolist currentTodolist;
    private Task currentTask;

    private TodolistService() {
        this.todolists = new ArrayList<>();
        this.addTodolist("Test");
        this.addTodolist("TO-DO");

        this.todolists.get(0).addTask(new Task("title", "miamiamiamaimaima"));
        this.todolists.get(0).addTask(new Task("title", "miamiamiamaimaima"));
        this.todolists.get(0).addTask(new Task("title", "miamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaimamiamiamiamaimaima"));
        this.todolists.get(0).addTask(new Task("title", "prouuuuuuuut"));
        this.todolists.get(1).addTask(new Task("title", "prouuuuuuuut"));
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

    public Todolist getCurrentTodolist() {
        return currentTodolist;
    }

    public void setCurrentTodolist(Todolist currentTodolist) {
        this.currentTodolist = currentTodolist;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }
}

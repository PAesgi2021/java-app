package fr.java.client.services;

import fr.java.client.entities.Task;
import fr.java.client.entities.Todolist;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodolistService {
    private static TodolistService instance;

//    private List<Todolist> todolists;
    private Todolist currentTodolist;
    private Task currentTask;

    private TodolistService() {
    }


    public static TodolistService getInstance() {
        if (TodolistService.instance == null) {
            instance = new TodolistService();
            return instance;
        }
        return instance;
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

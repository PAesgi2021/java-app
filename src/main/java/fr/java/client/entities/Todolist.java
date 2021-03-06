package fr.java.client.entities;

import fr.java.client.components.todolist.dto.TodolistDTO;

import java.util.ArrayList;
import java.util.List;

public class Todolist {

    private Integer    id;
    private String     title;
    private List<Task> tasks;
    private Integer    spaceId;

    public Todolist(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
        this.id = null;
    }

    public Todolist(TodolistDTO toAdapt) {
        this.title = toAdapt.getTitle();
        List<Task> tasklist = new ArrayList<>();
        if (toAdapt.getTasks() != null) {
            toAdapt.getTasks().forEach(task -> {
                tasklist.add(new Task(task));
            });
            this.tasks = tasklist;
        } else {
            this.tasks = new ArrayList<>();
        }

        this.id = toAdapt.getId();
    }

    public Todolist() {
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

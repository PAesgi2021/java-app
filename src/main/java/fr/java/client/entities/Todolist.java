package fr.java.client.entities;

import java.util.ArrayList;
import java.util.List;

public class Todolist {

    private String title;
    private List<Task> tasks;

    public Todolist(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
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
}

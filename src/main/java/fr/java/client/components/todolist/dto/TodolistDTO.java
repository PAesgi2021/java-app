package fr.java.client.components.todolist.dto;

import fr.java.client.components.createTask.dto.TaskDTO;

import java.util.List;

public class TodolistDTO {

    private Integer id;
    private String        title;
    private List<TaskDTO> tasks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}

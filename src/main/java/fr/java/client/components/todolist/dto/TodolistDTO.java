package fr.java.client.components.todolist.dto;

import fr.java.client.components.createTask.dto.TaskDTO;
import fr.java.client.entities.Todolist;

import java.util.List;

public class TodolistDTO {

    private Integer       id;
    private String        title;
    private List<TaskDTO> tasks;
    private Integer       spaceId;

    public TodolistDTO(Todolist todolist, Integer spaceId) {
        this.id = todolist.getId() != null ? todolist.getId() : null;
        this.title = todolist.getTitle();
        this.spaceId = spaceId;
    }

    public TodolistDTO() {
    }

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

package fr.java.client.components.space.dto;

import fr.java.client.components.login.dto.LoginDTO;
import fr.java.client.components.todolist.dto.TodolistDTO;
import fr.java.client.entities.Todolist;
import fr.java.client.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public class SpaceDTO {


    private String   name;
    private LoginDTO author;
    private String   visibility;
    private String         tag;
    private String         description;
    private String     lastUpdatedDate;
    private List<TodolistDTO> todolists;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoginDTO getAuthor() {
        return author;
    }

    public void setAuthor(LoginDTO author) {
        this.author = author;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<TodolistDTO> getTodolists() {
        return todolists;
    }

    public void setTodolists(List<TodolistDTO> todolists) {
        this.todolists = todolists;
    }
}

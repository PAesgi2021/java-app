package fr.java.client.entities;

import java.util.ArrayList;
import java.util.List;

public class Space {

    private String name;
    private User author;
    private String access;
    private String tag;
    private List<User> users;
    private List<Todolist> todolists;

    public Space(String title, User author, String access, String tag) {
        this.name = title;
        this.author = author;
        this.access = access;
        this.tag = tag;
        this.users = new ArrayList<>();
        this.todolists = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addTodolist(Todolist todolist) {
        this.todolists.add(todolist);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Todolist> getTodolists() {
        return todolists;
    }
}

package fr.java.client.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Space {

    private String name;
    private User author;
    private String visibility;
    private String tag;
    private String description;
    private LocalDateTime lastUpdatedDate;
    private List<User> users;
    private List<Todolist> todolists;

    public Space(String title, String visibility, String tag, User author) {
        this.name = title;
        this.author = author;
        this.visibility = visibility;
        this.tag = tag;
        this.lastUpdatedDate = LocalDateTime.now();

        this.users = new ArrayList<>();
        this.todolists = new ArrayList<>();
        this.addUser(author);
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

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Todolist> getTodolists() {
        return todolists;
    }

    @Override
    public String toString() {
        return "Space{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", visibility='" + visibility + '\'' +
                ", tag='" + tag + '\'' +
                ", description='" + description + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", users=" + users +
                ", todolists=" + todolists +
                '}';
    }
}

package fr.java.client.entities;

import fr.java.client.components.space.dto.SpaceDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Space {

    private Integer        id;
    private String         name;
    private User           author;
    private String         visibility;
    private String         tag;
    private String         description;
    private LocalDateTime  lastUpdatedDate;
    private List<Todolist> todolists;

    public Space(String title, String visibility, String tag, User author, String description) {
        this.name = title;
        this.author = author;
        this.visibility = visibility;
        this.tag = tag;
        this.lastUpdatedDate = LocalDateTime.now();
        this.todolists = new ArrayList<>();
        this.description = description;
    }

    public Space(SpaceDTO spaceDTO) {
        this.name = spaceDTO.getName();
        this.author = new User(spaceDTO.getAuthor().getUsername(), spaceDTO.getAuthor()
                                                                           .getPassword(), spaceDTO.getAuthor()
                                                                                                   .getId(), spaceDTO.getAuthor()
                                                                                                                     .getFirstname(), spaceDTO
                .getAuthor()
                .getLastname());
        this.visibility = spaceDTO.getVisibility();
        this.tag = spaceDTO.getTag();
        this.lastUpdatedDate = LocalDateTime.parse(spaceDTO.getLastUpdatedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
        List<Todolist> todolistList = new ArrayList<>();
        spaceDTO.getTodolists().forEach(todolist -> {
            Todolist toAdd = new Todolist(todolist);
            todolistList.add(toAdd);
        });
        this.todolists = todolistList;
        this.id = spaceDTO.getId();
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


    public List<Todolist> getTodolists() {
        return todolists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTodolists(List<Todolist> todolists) {
        this.todolists = todolists;
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
                ", todolists=" + todolists +
                '}';
    }
}

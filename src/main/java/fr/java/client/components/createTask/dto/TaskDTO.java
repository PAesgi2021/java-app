package fr.java.client.components.createTask.dto;

import fr.java.client.entities.Task;

import java.time.LocalDateTime;

public class TaskDTO {

    private String  title;
    private String  description;
    private String  deadLine;
    private String  finishedDate;
    private String  status;
    private int     limitDescription;
    private Integer todolistId;
    private Integer userId;

    public TaskDTO(Task task, Integer todolistId) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.deadLine = task.getDeadLine().toString();
        if (task.getFinishedDate() != null) {
            this.finishedDate = task.getFinishedDate().toString();
        }
        this.status = task.getStatus().toString();
        this.limitDescription = task.getLimitDescription();
        this.todolistId = todolistId;
    }

    public TaskDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLimitDescription() {
        return limitDescription;
    }

    public void setLimitDescription(int limitDescription) {
        this.limitDescription = limitDescription;
    }

    public Integer getTodolistId() {
        return todolistId;
    }

    public void setTodolistId(Integer todolistId) {
        this.todolistId = todolistId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

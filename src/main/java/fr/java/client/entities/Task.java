package fr.java.client.entities;

import fr.java.client.components.createTask.dto.TaskDTO;
import fr.java.client.utils.types.TaskStatusType;

import java.time.LocalDateTime;

public class Task {

    private String title;
    private String description;
    private LocalDateTime deadLine;
    private LocalDateTime finishedDate;
    private TaskStatusType status;
    private int limitDescription;
    private Integer todolistId;
    private Integer userId;

    public Task(String title, String description, LocalDateTime deadLine) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.status = TaskStatusType.todo;
        this.limitDescription = 1000;
    }

    public Task(TaskDTO toAdapt) {
        this.title = toAdapt.getTitle();
        this.description = toAdapt.getDescription();
        this.deadLine = LocalDateTime.parse(toAdapt.getDeadLine());
        this.finishedDate = LocalDateTime.parse(toAdapt.getFinishedDate());
        this.status = retrieveStatus(toAdapt.getStatus().toLowerCase());
        this.limitDescription = toAdapt.getLimitDescription();
        this.todolistId = toAdapt.getTodolistId();
        if (toAdapt.getUserId() != null) {
            this.userId = toAdapt.getUserId();
        }
    }


    public Task(String title, LocalDateTime deadLine) {
        this.title = title;
        this.deadLine = deadLine;
        this.status = TaskStatusType.todo;
        this.limitDescription = 1000;
    }

    private TaskStatusType retrieveStatus(String status) {
        if (status.equals(TaskStatusType.done.toString())) {
            return TaskStatusType.done;
        } else {
            return TaskStatusType.todo;
        }
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


    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public TaskStatusType getStatus() {
        return status;
    }

    public void setStatus(TaskStatusType status) {
        this.status = status;
    }

    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(LocalDateTime finishedDate) {
        this.finishedDate = finishedDate;
    }

    public int getLimitDescription() {
        return limitDescription;
    }

    public void setLimitDescription(int limitDescription) {
        this.limitDescription = limitDescription;
    }
}

package fr.java.client.components.createTask.dto;

import java.time.LocalDateTime;

public class TaskDTO {

    private String title;
    private String description;
    private LocalDateTime deadLine;
    private LocalDateTime finishedDate;
    private String status;
    private int limitDescription;

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

    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(LocalDateTime finishedDate) {
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
}

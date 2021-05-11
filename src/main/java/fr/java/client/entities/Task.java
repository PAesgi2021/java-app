package fr.java.client.entities;

import fr.java.client.utils.types.TaskStatusType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private String title;
    private String content;
    private LocalDateTime deadLine;
    private LocalDateTime finishedDate;
    private TaskStatusType status;

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
        this.deadLine = LocalDateTime.now().plusDays(10);
        this.status = TaskStatusType.todo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}

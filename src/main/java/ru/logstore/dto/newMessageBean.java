package ru.logstore.dto;

import ru.logstore.model.Level;

import java.time.LocalDateTime;

/**
 */
public class newMessageBean {
    private String dt;

    private String level;

    private String message;

    private String author;

    public newMessageBean() {
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}

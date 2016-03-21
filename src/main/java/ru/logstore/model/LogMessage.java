package ru.logstore.model;

import java.time.LocalDateTime;

/**
 */
public class LogMessage extends BaseEntity{
    protected final LocalDateTime dt;

    protected final Level level;

    protected final String message;

    protected final String author;

    public LogMessage(LocalDateTime dt, String author, String message, Level level) {
        this(null, dt, author, message, level);
    }

    public LogMessage(Integer id, LocalDateTime dt, String author, String message, Level level) {
        super(id);
        this.id = id;
        this.dt = dt;
        this.author = author;
        this.message = message;
        this.level = level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public String getMessage() {
        return message;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    public Level getLevel() {
        return level;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "id=" + id +
                ", dateTime=" + dt +
                ", message='" + message + '\'' +
                '}';
    }
}

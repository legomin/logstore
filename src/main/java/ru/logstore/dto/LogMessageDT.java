package ru.logstore.dto;

import ru.logstore.model.Level;

import java.time.LocalDateTime;

/**
 */
public class LogMessageDT {
    protected final String dt;

    protected final Level level;

    protected final String message;

    public LogMessageDT(LocalDateTime dt, String message, Level level) {
        this.dt = dt.toString(); //TODO ISO 8601 format
        this.message = message;
        this.level = level;
    }

    public String getDt() {
        return dt;
    }

    public String getMessage() {
        return message;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                ", dateTime=" + dt +
                ", message='" + message + '\'' +
                '}';
    }
}

package ru.logstore.dto;

import ru.logstore.model.BaseEntity;
import ru.logstore.model.Level;

import java.time.LocalDateTime;

/**
 */
public class LogMessageDT {
    protected final LocalDateTime dt;

    protected final Level level;

    protected final String message;

    public LogMessageDT(LocalDateTime dt, String message, Level level) {
        this.dt = dt;
        this.message = message;
        this.level = level;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public String getMessage() {
        return message;
    }



    @Override
    public String toString() {
        return "LogMessage{" +
                ", dateTime=" + dt +
                ", message='" + message + '\'' +
                '}';
    }
}

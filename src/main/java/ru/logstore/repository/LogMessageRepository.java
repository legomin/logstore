package ru.logstore.repository;

import ru.logstore.model.LogMessage;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 */
public interface LogMessageRepository {
    //
    LogMessage save(LogMessage logMessage);

    // ORDERED DATE, TIME
    Collection<LogMessage> getAll();

    // ORDERED DATE, TIME
    Collection<LogMessage> getPage(int page, int size);
}

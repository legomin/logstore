package ru.logstore.repository;

import ru.logstore.model.LogMessage;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 */
public interface LogMessageRepository {
    //
    LogMessage save(LogMessage logMessage);

    // ORDERED DATE, TIME
    List<LogMessage> getAll();

    // ORDERED DATE, TIME
    List<LogMessage> getPage(int page, int size);
}

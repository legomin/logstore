package ru.logstore.repository.mock;

import org.springframework.stereotype.Repository;
import ru.logstore.model.Level;
import ru.logstore.model.LogMessage;
import ru.logstore.repository.LogMessageRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 */
@Repository
public class InMemoryLogMessageRepositoryImpl implements LogMessageRepository {

    public static final Comparator<LogMessage> USER_MEAL_COMPARATOR = (um1, um2) -> um2.getDt().compareTo(um1.getDt());

    private Map<Integer, LogMessage> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    { //hardcode mock db
        Integer id = counter.incrementAndGet();
        save(new LogMessage(id, LocalDateTime.of(2016, Month.MARCH, 20, 10, 0), "user", "debug1", Level.DEBUG));
        id = counter.incrementAndGet();
        save(new LogMessage(id, LocalDateTime.of(2016, Month.MARCH, 20, 11, 0), "admin", "debug2", Level.DEBUG));
        id = counter.incrementAndGet();
        save(new LogMessage(id, LocalDateTime.of(2016, Month.MARCH, 20, 12, 0), "user", "error1", Level.ERROR));
        id = counter.incrementAndGet();
        save(new LogMessage(id, LocalDateTime.of(2016, Month.MARCH, 20, 13, 0), "admin", "info1", Level.INFO) );
    }

    public InMemoryLogMessageRepositoryImpl() {
    }

    @Override
    public LogMessage save(LogMessage logMessage) {
        Objects.requireNonNull(logMessage);

        Integer logMessageId = logMessage.getId();

        if (logMessage.isNew()) {
            logMessageId = counter.incrementAndGet();
            logMessage.setId(logMessageId);
        }
        repository.put(logMessageId, logMessage);
        return logMessage;
    }

    @Override
    public Collection<LogMessage> getAll() {
        return repository.values().stream()
                .sorted(USER_MEAL_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public Collection<LogMessage> getPage(int page, int size) {
        if (size <= 0 || page < 0) {
            return getAll();
        }
        return getAll().stream()
                .skip(page * size)
                .limit(size)
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
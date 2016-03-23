package ru.logstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.logstore.dto.LogMessageDT;

import ru.logstore.dto.NewMessageBean;
import ru.logstore.model.Level;
import ru.logstore.model.LogMessage;
import ru.logstore.repository.LogMessageRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

/**

 */
@Service
public class LogMessageService {

    @Autowired LogMessageRepository repository;

    public LogMessage save(LogMessage logMessage) {
        return repository.save(logMessage);
    }

    public Collection<LogMessage> getAll() {
        return repository.getAll();
    }

    public Collection<LogMessage> getPage(int page, int size) {
        return repository.getPage(page, size);
   }



    public static List<LogMessageDT> getLogMessagesDT(Collection<LogMessage> logMessages) {

        return logMessages.stream()
                .map(lm -> new LogMessageDT(lm.getDt(), lm.getMessage(), lm.getLevel()))
                .collect(Collectors.toList());
    }

    public static Map<String,Object> validateNewMessage(NewMessageBean newMessage) {

        Map<String,Object> result = new HashMap<>();
        List<Error> errors = new ArrayList<>();

        LocalDateTime dt = null;
        Level level = null;

        //date
        try {
            dt = LocalDateTime.parse(newMessage.getDt());
        }
        catch (DateTimeParseException e) {
            errors.add(new Error("dt", "Wrong date"));
            result.put("result", 400);
        }

        //level
        try {
            level = Level.valueOf(newMessage.getLevel());
        }
        catch (IllegalArgumentException e) {
            errors.add(new Error("level", "Invalid level"));

            result.put("result", 400);
        }

        if (result.get("result") == null) {
            result.put("result", 200);
            LogMessage logMessage = new LogMessage(dt, newMessage.getAuthor(), newMessage.getMessage(), level);
            result.put("logMessage", logMessage);
        }
        else {
            result.put("errors", errors);
        }
        return result;
    }

    public static class Error {
        public final String field;
        public final String message;

        public Error(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }

}

package ru.logstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.logstore.LoggerWrapper;
import ru.logstore.dto.LogMessageDT;
import ru.logstore.model.LogMessage;
import ru.logstore.repository.LogMessageRepository;
import ru.logstore.repository.UserRepository;
import ru.logstore.repository.mock.InMemoryLogMessageRepositoryImpl;
import ru.logstore.util.LogMessageUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 */
@RestController
public class MainController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MainController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogMessageRepository logMessageRepository;

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public List<LogMessageDT> getAll() {
        //LOG.info("getAll for User {}", userId);
        return LogMessageUtil.getLogMessagesDT(logMessageRepository.getAll());
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public void create(LogMessage logMessage) {
        //int userId = LoggedUser.id();
        //LOG.info("create {} for User {}", meal, userId);
        logMessageRepository.save(logMessage);
    }

 }
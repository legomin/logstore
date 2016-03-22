package ru.logstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.logstore.LoggerWrapper;
import ru.logstore.dto.*;
import ru.logstore.repository.LogMessageRepository;
import ru.logstore.repository.UserRepository;
import ru.logstore.util.LogMessageUtil;

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
    public List<LogMessageDT> getAll(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size) {
        //LOG.info("getAll for User {}", userId);
        return LogMessageUtil.getLogMessagesDT(logMessageRepository.getPage(page == null ? 0 : page.intValue(), size == null ? 0 : size.intValue()));
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public void create(newMessageBean newMessage, Model model) {
        //int userId = LoggedUser.id();
        LOG.info("create {} for User {}", "","");
        //logMessageRepository.save(newMessage);
    }

 }
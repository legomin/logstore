package ru.logstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.logstore.LoggerWrapper;
import ru.logstore.dto.*;
import ru.logstore.model.LogMessage;
import ru.logstore.service.LogMessageService;
import ru.logstore.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@RestController
public class MainController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MainController.class);

    @Autowired
    private LogMessageService logMessageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>>  getAll(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size) {

        Map<String, Object> resultMap = new HashMap<>();
        String loggedUser = userService.getLoggedUser();
        if ("NotAuthorised".equals(loggedUser)) {
            LOG.info("authtoriation error");
            resultMap.put("message","Access denied");
            return new ResponseEntity<>(resultMap, HttpStatus.UNAUTHORIZED);
        }

        LOG.info("getAll for logmessages ");
        resultMap.put("logs", logMessageService.getLogMessagesDT(logMessageService.getPage(page == null ? 0 :
                page.intValue(), size == null ? 0 : size.intValue())) );

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> create(@RequestBody NewMessageBean newMessage) {

        Map<String, Object> resultMap = new HashMap<>();
        String loggedUser = userService.getLoggedUser();
        if ("NotAuthorised".equals(loggedUser)) {
            LOG.info("authtoriation error");
            resultMap.put("message","Access denied");
            return new ResponseEntity<>(resultMap, HttpStatus.UNAUTHORIZED);
        }
        else if ("other".equals(loggedUser)) {
            LOG.info("forbiden");
            resultMap.put("message","User other does not have access");
            return new ResponseEntity<>(resultMap, HttpStatus.FORBIDDEN);
        }

        Map<String, Object> validationMap = logMessageService.validateNewMessage(newMessage, loggedUser);

        if ((Integer) validationMap.get("result") == 200) {
            LOG.info("create new log" );
            resultMap.put("id", logMessageService.save((LogMessage) validationMap.get("logMessage")).getId());
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        else {
            LOG.info("error creation new log" );
            resultMap.put("errors",validationMap.get("errors"));
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

 }
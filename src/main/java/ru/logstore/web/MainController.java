package ru.logstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.logstore.LoggerWrapper;
import ru.logstore.dto.*;
import ru.logstore.model.LogMessage;
import ru.logstore.service.LogMessageService;

import javax.validation.Valid;
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

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public List<LogMessageDT>  getAll(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size) {

        LOG.info("getAll for logmessages ");
        return logMessageService.getLogMessagesDT(logMessageService.getPage(page == null ? 0 :
                page.intValue(), size == null ? 0 : size.intValue())) ;
   }

    @RequestMapping(value = "/logs", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> create(@Valid @RequestBody NewMessageBean newMessage,
                                                     Authentication authentication) {

        Map<String, Object> resultMap = new HashMap<>();
        String loggedUser = (String) authentication.getPrincipal();
        if ("other".equals(loggedUser)) {
            LOG.info("forbiden");
            resultMap.put("message","User1 other does not have access");
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
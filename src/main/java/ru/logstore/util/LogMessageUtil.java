package ru.logstore.util;

import ru.logstore.dto.LogMessageDT;

import ru.logstore.model.LogMessage;

import java.util.*;
import java.util.stream.Collectors;

/**

 */
public class LogMessageUtil {

     public static List<LogMessageDT> getLogMessagesDT(Collection<LogMessage> logMessages) {

        return logMessages.stream()
                .map(lm -> new LogMessageDT(lm.getDt(), lm.getMessage(), lm.getLevel()))
                .collect(Collectors.toList());
    }


}

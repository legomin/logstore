package ru.logstore.repository.mock;

import org.junit.Assert;
import org.junit.Test;
import ru.logstore.model.Level;
import ru.logstore.model.LogMessage;
import ru.logstore.repository.LogMessageRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 */
public class InMemoryLogMessageRepositoryImplTest {

    LogMessageRepository logMessageRepository = new InMemoryLogMessageRepositoryImpl();

    @Test
    public void testSave() throws Exception {
        LogMessage newMessage = new LogMessage(null, LocalDateTime.of(2016, Month.MARCH, 27, 13, 0), "user", "info3", Level.INFO);
        Assert.assertEquals((int)logMessageRepository.save(newMessage).getId(), 5);
    }

    @Test
    public void testGetAll() throws Exception {
        List<LogMessage> list =  logMessageRepository.getAll();
        Assert.assertEquals(list.size(), 4);
        Assert.assertEquals(list.get(0).getMessage(),"info1");
        Assert.assertEquals(list.get(3).getMessage(),"debug1");
    }

    @Test
    public void testGetPage() throws Exception {
        List<LogMessage> list =  logMessageRepository.getPage(0, 2);
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0).getMessage(),"info1");
        Assert.assertEquals(list.get(1).getMessage(),"error1");

        list =  logMessageRepository.getPage(1, 2);
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0).getMessage(),"debug2");
        Assert.assertEquals(list.get(1).getMessage(),"debug1");
   }
}
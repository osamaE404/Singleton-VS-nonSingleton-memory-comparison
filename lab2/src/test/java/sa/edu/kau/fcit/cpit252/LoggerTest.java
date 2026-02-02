package sa.edu.kau.fcit.cpit252;

import org.junit.Test;
import sa.edu.kau.fcit.cpit252.logging.Logger;

import static org.junit.Assert.assertEquals;

public class LoggerTest {

    @Test
    public void shouldBeIdenticals()
    {
        Logger log1 =  Logger.getLoggerInstance();
        Logger log2 =  Logger.getLoggerInstance();
        assertEquals(log1, log2);
    }
}

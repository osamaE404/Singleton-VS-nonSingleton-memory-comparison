package sa.edu.kau.fcit.cpit252.logging;

public class Logger {
    private static Logger LoggerInstence;
    private static int instanceCount = 0;
    private byte[] weight = new byte[1024 * 100]; // 100KB weight

    public static Logger getLoggerInstance() {
        if (LoggerInstence == null){
            LoggerInstence = new Logger();
        }
        return LoggerInstence;
    }

    public static Logger getNonSingletonLoggerInstance() {
        return new Logger();
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public static void resetInstanceCount() {
        instanceCount = 0;
    }

    private Logger() {
        instanceCount++;
    }
    
    public void info (String message) {
    }
    public void error (String message) {
    }
}

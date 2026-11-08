/*
 Requirements
    The logging framework should support different log levels, such as DEBUG, INFO, WARNING, ERROR, and FATAL.
    It should allow logging messages with a timestamp, log level, and message content.
    The framework should support multiple output destinations, such as console, file, and database.
    It should provide a configuration mechanism to set the log level and output destination.
    The logging framework should be thread-safe to handle concurrent logging from multiple threads.
    It should be extensible to accommodate new log levels and output destinations in the future.
 * 
 * 
 */


 // execute & check

enum LogLevel {
   DEBUG, INFO, WARNING, ERROR, FATAL
}

interface OutputDestination {
    void write(LogLevel logLevel, String messageContent);
}

class Console extends OutputDestination {

    @Override
    public void write(LogLevel logLevel, String messageContent) {
        System.out.println(logLevel + ": "+ messageContent);
    }
}

class File extends OutputDestination {

    @Override
    public void write(LogLevel logLevel, String messageContent) {
        System.out.println("Writting to file --- " + logLevel + ": "+ messageContent);
    }
}

class Database extends OutputDestination {

    @Override
    public void write(LogLevel logLevel, String messageContent) {
        System.out.println("Writting to database --- " + logLevel + ": "+ messageContent);
    }
}

class Logger {
    private LogLevel logLevel;

    public Logger() {

    }

    public Logger(LogLevel logLevel, OutputDestination output) {
        this.logLevel = logLevel;
        this.output = output;
    }

    public void setLogLevel(LogLevel logLevel) {
         this.logLevel = logLevel;
    }

    public void setOutputDestination(OutputDestination output) {
        this.output = output;
    }

    public synchronized void log(String messageContent) {
        if (this.output == null) {
            throw new Exception("output destination is not set");
        }
        if (this.logLevel == null) {
            throw new Exception("log level is not set");
        }

        this.output.write(logLevel, messageContent);
    }

}

public class LogManager {
    public static volatile Logger logger; 
    // volatile keyword so that even some other thread changes logger value it is reflected to all other threads immediately

    public static Logger getLogger() { // avoid synchronization for entire method, as it will cause synchronization performace issue even after logger initialization.
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger().setLogLevel(LogLevel.INFO);
                    logger.setOutputDestination(new File());
                }
            }
        }
        return logger;
    }
}



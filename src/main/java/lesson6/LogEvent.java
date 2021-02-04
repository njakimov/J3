package lesson6;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.*;

public class LogEvent {

    private final Logger logger;
    private Handler handler;
    private String className;
    private String fileLogName = "log.txt";

    public LogEvent(String className) {
        this.className = className;
        this.logger = Logger.getLogger(className);
        initLogger();
        initHandler();

    }

    public LogEvent(String className, String fileLogName) {
        this.className = className;
        this.logger = Logger.getLogger(className);
        this.fileLogName = fileLogName;
        initLogger();
        initHandler();
    }

    private void initLogger() {
        logger.setLevel(Level.FINE);
    }

    private void initHandler() {
        try {
            handler = new FileHandler(fileLogName, 1024 * 5, 5, true);
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return LocalDateTime.now() + " : " + className + " : " + record.getMessage() + "\n";
                }
            });
            logger.addHandler(handler);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Ошибка создания handler");
        }
    }

    public void log(Level level, String message) {
        logger.log(level, message);
    }


}

package com.server;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.*;
// 1. Добавить на серверную сторону чата логирование, с выводом информации о действиях на сервере
//      (запущен, произошла ошибка, клиент подключился, клиент прислал сообщение/команду).
public class LogEvent {

    private final Logger logger;
    private Handler handler;
    private String fileLogName = "log.txt";

    public LogEvent(String className) {
        this.logger = Logger.getLogger(className);
        initLogger();
        initHandler();

    }

    public LogEvent(String className, String fileLogName) {
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
                    return LocalDateTime.now() + " : " + record.getMessage() + "\n";
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

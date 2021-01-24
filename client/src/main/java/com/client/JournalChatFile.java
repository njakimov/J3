package com.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

// 1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
// 2. После загрузки клиента показывать ему последние 100 строк чата.
public class JournalChatFile {

    private final static String journalFileNamePostfix = "Messages.txt";                                                // постфикс файла журнала пользователя
    private final static String journalPathName = "journal";                                                            // путь до файла журнала пользователя
    private final static Integer limitMessage = 100;                                                                    // лимит последних выводимых сообщений

    /**
     * Метод записи сообщения в файл истории сообщений
     *
     * @param messageChat - сообщение, записываемое в лог
     * @param nickname    - имя пользователя
     */
    public static void saveHistoryMessage(String messageChat, String nickname) {
        String userFileName = nickname + journalFileNamePostfix;
        try {

            if (isExistPath(journalPathName) && isExistFile(journalPathName + "/" + userFileName)) {
                Path path = Paths.get(journalPathName + "/" + userFileName);

                List<String> rows = new ArrayList<>();                                                                  // вероятно, тут должен быть более красивый способ
                rows.add(messageChat);                                                                                  // прошу прокомментировать

                Files.write(path, rows, StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Метод чтения сообщений из файла истории сообщений
     *
     * @param nickname - имя пользователя
     */
    public static String readHistoryMessage(String nickname) {
        String messagesUser = "";
        try {
            String messagesUserTemp = "";
            String userFileName = nickname + journalFileNamePostfix;

            if (isExistPath(journalPathName) && isExistFile(journalPathName + "/" + userFileName)) {
                Path path = Paths.get(journalPathName + "/" + userFileName);

                messagesUserTemp = new String(Files.readAllBytes(path));

                String[] messagesArray = messagesUserTemp.split("(/\\r\\n|\\r|\\n/g)");

                if (messagesArray.length > limitMessage) {
                    StringBuilder messagesUserBuffer = new StringBuilder();
                    for (int i = messagesArray.length - limitMessage - 1; i < messagesArray.length; i++) {
                        messagesUserBuffer.append(messagesArray[i]);
                    }
                    messagesUser = messagesUserBuffer.toString();
                } else {
                    messagesUser = messagesUserTemp;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return messagesUser;
    }

    /**
     * Метод проверки существования директории и создания ее при отсутствии
     *
     * @param stringPath - путь до директории
     */
    public static boolean isExistPath(String stringPath) {
        boolean isExistPath = true;
        try {

            if (stringPath.isEmpty()) {
                throw new IOException("Путь пустой");
            }

            Path path = Paths.get(stringPath);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            isExistPath = false;
        }
        return isExistPath;
    }

    /**
     * Метод проверки существования файла и создания его при отсутствии
     *
     * @param stringPathToFile - путь до файла
     */
    public static boolean isExistFile(String stringPathToFile) {
        boolean isExistFile = true;
        try {

            if (stringPathToFile.isEmpty()) {
                throw new IOException("Путь пустой");
            }

            Path path = Paths.get(stringPathToFile);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            isExistFile = false;
        }
        return isExistFile;
    }
}

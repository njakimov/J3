package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class Server {
    private Vector<ClientHandler> clients;
    private AuthService authService;
    private ExecutorService executorService;
    private final LogEvent logger = new LogEvent(Server.class.getName());

    public AuthService getAuthService() {
        return authService;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public Server() {
        clients = new Vector<>();
        authService = new DbAuthService();
        executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            this.logger.log(Level.INFO, "Сервер запущен на порту 8189");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
                this.logger.log(Level.INFO, "Подключился новый клиент");
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.logger.log(Level.SEVERE, e.getMessage());
            DbService.connectionClose();
        }
        executorService.shutdown();
        this.logger.log(Level.INFO, "Сервер завершил свою работу");
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void privateMsg(ClientHandler sender, String receiverNick, String msg) {
        if (sender.getNickname().equals(receiverNick)) {
            sender.sendMsg("заметка для себя: " + msg);
            return;
        }
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(receiverNick)) {
                o.sendMsg("от " + sender.getNickname() + ": " + msg);
                sender.sendMsg("для " + receiverNick + ": " + msg);
                return;
            }
        }
        sender.sendMsg("Клиент " + receiverNick + " не найден");
    }

    public void changeNickName(ClientHandler sender, String login, String newNickName) {
//        if (sender.getNickname().equals(receiverNick)) {
//            sender.sendMsg("заметка для себя: " + msg);
//            return;
//        }
//        for (ClientHandler o : clients) {
//            if (o.getNickname().equals(receiverNick)) {
//                o.sendMsg("от " + sender.getNickname() + ": " + msg);
//                sender.sendMsg("для " + receiverNick + ": " + msg);
//                return;
//            }
//        }
//        sender.sendMsg("Клиент " + receiverNick + " не найден");
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClientsList();
    }

    public boolean isNickBusy(String nickname) {
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder(15 * clients.size());
        sb.append("/clients ");
        // '/clients '
        for (ClientHandler o : clients) {
            sb.append(o.getNickname()).append(" ");
        }
        // '/clients nick1 nick2 nick3 '
        sb.setLength(sb.length() - 1);
        // '/clients nick1 nick2 nick3'
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }
}

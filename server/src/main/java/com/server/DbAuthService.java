package com.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

// 1. Добавить в сетевой чат авторизацию через базу данных SQLite.
// 2.*Добавить в сетевой чат возможность смены ника.
public class DbAuthService implements AuthService {
    private class UserData {
        private String login;
        private String password;
        private String nickname;


        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    private List<UserData> users;
    private final LogEvent logger = new LogEvent(DbAuthService.class.getName());

    public DbAuthService() {
        this.users = new ArrayList<>();
        DbService.getInstance();
        DbService.connectionStart();
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        try {
            String queryString = "select * from user where login='" + login + "' AND pwd='" + password + "' LIMIT 1";
            ResultSet resultSet = DbService.executeQuery(queryString);
            while (resultSet.next()) {
                users.add(new UserData(login, password, resultSet.getString("nickname")));
                return resultSet.getString("nickname");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.logger.log(Level.INFO, "Не верный логин или пароль\nlogin: " + login + " password: " + password);
        return null;
    }
}

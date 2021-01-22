package com.server;

import java.sql.*;

public class DbService {
    private static DbService instance;
    private static Connection conn;
    private static Boolean connState = false;

    private DbService() {
    }

    public static DbService getInstance() {
        if (instance == null) {        //если объект еще не создан
            instance = new DbService();    //создать новый объект
        }
        return instance;
    }

    public static void connectionStart() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:WorkDB.db";
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                connState = true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Boolean getConnState() {
        return connState;
    }

    public static Connection getConn() {
        return conn;
    }

    public static ResultSet executeQuery(String queryText) {
        try {
            Statement query = conn.createStatement();
            return query.executeQuery(queryText);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void connectionClose() {
        try {
            conn.close();
            connState = false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

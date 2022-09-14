package com.ibtech.mall.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {

    public static final String DB_DRIVER = System.getenv("DB_DRIVER");
    public static final String DB_URL = System.getenv("DB_URL");
    public static final String DB_USER = System.getenv("DB_USER");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    protected Connection connection;

    public DbCon() {
        try {

            Class.forName(DB_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

    }

    protected void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

package com.ibtech.mall.database.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {

    private static final String DB_DRIVER = System.getenv("DB_DRIVER");
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static Logger logger = LoggerFactory.getLogger(DbCon.class);
    protected Connection connection;

    public DbCon() {
        try {

            Class.forName(DB_DRIVER);
            logger.info("PostgreSQL connecting: " + DB_DRIVER);
        } catch (Exception e) {
            logger.error("PostgreSQL driver problem: " + e.getMessage());
        }
    }

    protected void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Database connected");
        } catch (SQLException e) {
            logger.error("Database connecting error: " + e.getMessage());
        }


    }

    protected void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database disconnected");
            } catch (SQLException e) {
                logger.error("Database disconnecting error: " + e.getMessage());
            }
        } else {
            logger.info("Database connection not found ");
        }
    }
}

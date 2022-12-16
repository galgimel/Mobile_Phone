package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    Properties properties;

    public ConnectionFactory(Properties properties) {
        this.properties = properties;
    }

    public Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Соединение не закрыто.");
        }
    }
}
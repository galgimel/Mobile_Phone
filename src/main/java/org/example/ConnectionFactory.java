package org.example;

import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.example.Constance.*;

@AllArgsConstructor
public class ConnectionFactory {
    final Properties properties;

    public Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password")
            );
        } catch (final ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void closeConnection(final Connection connection) {
        try {
            connection.close();
        } catch (final SQLException e) {
            System.out.println(ERR_CONNECTION);
        }
    }
}
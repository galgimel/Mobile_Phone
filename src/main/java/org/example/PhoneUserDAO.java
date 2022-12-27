package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneUserDAO {
    ConnectionFactory connectionFactory;

    public PhoneUserDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    public List<PhoneUser> findAll() {
        Connection connection = connectionFactory.createConnection();
        List<PhoneUser> phoneUsers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM phone_user;")
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                phoneUsers.add(
                    new PhoneUser(
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mobile_phone")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return phoneUsers;
    }
    public List<PhoneUser> findBrandUsers(String brand) {
        Connection connection = connectionFactory.createConnection();
        List<PhoneUser> phoneUsers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM phone_user\n" +
                    "WHERE mobile_phone IN (\n" +
                    "    SELECT id\n" +
                    "    FROM mobile_phones\n" +
                    "    WHERE brand = '%s'\n" +
                    "    );", brand)
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                phoneUsers.add(
                    new PhoneUser(
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mobile_phone")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return phoneUsers;
    }

    public List<PhoneUser> findNoneUsers() {
        Connection connection = connectionFactory.createConnection();
        List<PhoneUser> phoneUsers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM phone_user\n" +
                    "WHERE mobile_phone IS NULL;")
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                phoneUsers.add(
                    new PhoneUser(
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mobile_phone")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return phoneUsers;
    }
}

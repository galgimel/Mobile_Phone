package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneUserDAO {
    final ConnectionFactory connectionFactory;

    public PhoneUserDAO(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<PhoneUser> findAll() {
        final Connection connection = connectionFactory.createConnection();
        List<PhoneUser> phoneUsers = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM phone_user;"
            );
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                phoneUsers.add(
                    new PhoneUser(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mobile_phone_id")
                    )
                );
            }
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return phoneUsers;
    }

    public void setMobilePhoneIDNull(final int id){
        final Connection connection = connectionFactory.createConnection();
        try {
            final PreparedStatement statement = connection.prepareStatement(
              String.format("UPDATE phone_user\n" +
                  "SET mobile_phone_id = NULL\n" +
                  "WHERE mobile_phone_id = '%d';", id)
            );
            statement.execute();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public int findBrandUsers(final String brand) {
        final Connection connection = connectionFactory.createConnection();
        int count = 0;
        try {
            final PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT COUNT(pu.name) AS users\n" +
                    "FROM mobile_phone  mp INNER JOIN phone_user pu\n" +
                    "ON mp.id = pu.mobile_phone_id AND mp.brand = '%s';", brand)
            );
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("users");
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return count;
    }

    public int findNoneUsers() {
        final Connection connection = connectionFactory.createConnection();
        int count = 0;
        try {
            final PreparedStatement statement = connection.prepareStatement(
                "SELECT COUNT(phone_user.name) AS count FROM phone_user\n" +
                    "WHERE mobile_phone_id IS NULL;"
            );
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");

        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return count;
    }
}
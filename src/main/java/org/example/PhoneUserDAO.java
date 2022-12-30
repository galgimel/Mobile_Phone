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
                "SELECT * FROM phone_user;"
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                phoneUsers.add(
                    new PhoneUser(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mobile_phone_id")
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

    public String findBrandUsers(String brand) {
        Connection connection = connectionFactory.createConnection();
        List<PhoneUser> phoneUsers = new ArrayList<>();
        List<MobilePhone> mobilePhones = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int count = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("""
                    SELECT pu.id, name, mobile_phone_id, mp.id, brand, model,
                           performance, price
                    FROM mobile_phone  mp INNER JOIN phone_user pu
                    ON mp.id = pu.mobile_phone_id
                    WHERE mobile_phone_id IN (
                        SELECT id
                        FROM mobile_phone
                        WHERE brand = '%s'
                    );""", brand)
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                count++;
                phoneUsers.add(
                    new PhoneUser(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mobile_phone_id")
                    )
                );
                mobilePhones.add(
                    new MobilePhone(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("performance"),
                        resultSet.getInt("price")
                    )
                );
                result.append(phoneUsers.get(count));
                result.append(mobilePhones.get(count));
                result.append("\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return result.toString();
    }

    public List<PhoneUser> findNoneUsers() {
        Connection connection = connectionFactory.createConnection();
        List<PhoneUser> phoneUsers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM phone_user WHERE mobile_phone_id IS NULL;"
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                phoneUsers.add(
                    new PhoneUser(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mobile_phone_id")
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

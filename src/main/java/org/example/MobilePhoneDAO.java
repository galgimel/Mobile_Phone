package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilePhoneDAO {
    ConnectionFactory connectionFactory;

    public MobilePhoneDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void save(MobilePhone mobilePhone) {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('%s', '%s', %d, %d);",
                    mobilePhone.brand, mobilePhone.model, mobilePhone.performance, mobilePhone.price)
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public void delete(String brand, String model) {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("DELETE FROM mobile_phones WHERE brand = '%s' AND model = '%s';", brand, model)
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public void createBase(String createBase) {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(createBase);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public List<MobilePhone> findAll() {
        Connection connection = connectionFactory.createConnection();
        List<MobilePhone> mobilePhone = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM mobile_phones;")
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                mobilePhone.add(
                    new MobilePhone(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("performance"),
                        resultSet.getInt("price")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return mobilePhone;
    }

    public List<MobilePhone> findByPrice(int price) {
        Connection connection = connectionFactory.createConnection();
        List<MobilePhone> mobilePhone = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM mobile_phones WHERE price > %d;", price)
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                mobilePhone.add(
                    new MobilePhone(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("performance"),
                        resultSet.getInt("price")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return mobilePhone;
    }

    public List<MobilePhone> findByPerformance(int performance) {
        Connection connection = connectionFactory.createConnection();
        List<MobilePhone> mobilePhone = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM mobile_phones WHERE performance > %d;", performance)
            );
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                mobilePhone.add(
                    new MobilePhone(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("performance"),
                        resultSet.getInt("price")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return mobilePhone;
    }
}

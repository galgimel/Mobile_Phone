package org.example;

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
                String.format("INSERT INTO mobile_phone (brand, model, performance, price) VALUES ('%s', '%s', %d, %d);",
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
                String.format("DELETE FROM mobile_phone WHERE brand = '%s' AND model = '%s';", brand, model)
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
                "SELECT * FROM mobile_phone;"
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
                String.format("SELECT * FROM mobile_phone WHERE price > %d;", price)
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
                String.format("SELECT * FROM mobile_phone WHERE performance > %d;", performance)
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

    public List<MobilePhone> findPhonesByStore(String store) {
        Connection connection = connectionFactory.createConnection();
        List<MobilePhone> mobilePhones = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format(
                    "SELECT  brand, model\n" +
                        "FROM mobile_phone mp INNER JOIN mobile_store ms\n" +
                        "ON mp.id = ms.mobile_phone_id AND store_id = (\n" +
                        "        SELECT id\n" +
                        "        FROM store\n" +
                        "        WHERE name = '%s'\n" +
                        "        );", store
                )
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mobilePhones.add(
                    new MobilePhone(
                        resultSet.getString("brand"),
                        resultSet.getString("model")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return mobilePhones;
    }
    public int findIDByBrandAndModel(String brand, String model) {
        Connection connection = connectionFactory.createConnection();
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT id\n" +
                    "FROM mobile_phone\n" +
                    "WHERE brand = '%s' AND model = '%s';", brand, model)
            );
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return id;
    }
}
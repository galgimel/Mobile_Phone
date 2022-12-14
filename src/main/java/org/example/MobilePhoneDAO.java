package org.example;

import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class MobilePhoneDAO {
    final ConnectionFactory connectionFactory;

    public void save(final MobilePhone mobilePhone) {
        final Connection connection = connectionFactory.createConnection();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                String.format("INSERT INTO mobile_phone (brand, model, performance, price) VALUES ('%s', '%s', %d, %d);",
                    mobilePhone.brand, mobilePhone.model, mobilePhone.performance, mobilePhone.price)
            );
            statement.execute();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public void delete(final String brand, final String model) {
        final Connection connection = connectionFactory.createConnection();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                String.format("DELETE FROM mobile_phone WHERE brand = '%s' AND model = '%s';", brand, model)
            );
            statement.execute();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public void createBase(final String createBase) {
        final Connection connection = connectionFactory.createConnection();
        try {
            final PreparedStatement statement = connection.prepareStatement(createBase);
            statement.execute();
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public List<MobilePhone> findAll() {
        final Connection connection = connectionFactory.createConnection();
        List<MobilePhone> mobilePhone = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM mobile_phone;"
            );
            final ResultSet resultSet = statement.executeQuery();

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
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return mobilePhone;
    }

    public List<MobilePhone> findByPrice(final int price) {
        final Connection connection = connectionFactory.createConnection();
        List<MobilePhone> mobilePhone = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM mobile_phone WHERE price > %d;", price)
            );
            final ResultSet resultSet = statement.executeQuery();

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
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return mobilePhone;
    }

    public List<MobilePhone> findByPerformance(final int performance) {
        final Connection connection = connectionFactory.createConnection();
        List<MobilePhone> mobilePhone = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM mobile_phone WHERE performance > %d;", performance)
            );
            final ResultSet resultSet = statement.executeQuery();

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
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return mobilePhone;
    }

    public List<MobilePhone> findPhonesByStore(final int storeID) {
        final Connection connection = connectionFactory.createConnection();
        List<MobilePhone> mobilePhones = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                String.format(
                    "SELECT  brand, model\n" +
                        "FROM mobile_phone mp\n" +
                        "INNER JOIN mobile_phone_to_store ms\n" +
                        "ON mp.id = ms.mobile_phone_id AND store_id = '%d';", storeID
                )
            );
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mobilePhones.add(
                    new MobilePhone(
                        resultSet.getString("brand"),
                        resultSet.getString("model")
                    )
                );
            }
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return mobilePhones;
    }
    public int findIDByBrandAndModel(final String brand, final String model) {
        final Connection connection = connectionFactory.createConnection();
        int id = 0;
        try {
            final PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT id\n" +
                    "FROM mobile_phone\n" +
                    "WHERE brand = '%s' AND model = '%s';", brand, model)
            );
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id");
        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return id;
    }
}
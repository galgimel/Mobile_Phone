package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {
    final ConnectionFactory connectionFactory;

    public StoreDAO(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Store> findAll() {
        Connection connection = connectionFactory.createConnection();
        List<Store> store = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM store;"
            );
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                store.add(
                    new Store(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return store;
    }

    public List<Store> findStoresByPhone(final int mobilePhoneID) {
        Connection connection = connectionFactory.createConnection();
        List<Store> store = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(
                String.format(
                    "SELECT s.id, name\n" +
                     "FROM store s\n" +
                     "INNER JOIN mobile_phone_to_store ms\n" +
                     "ON s.id = ms.store_id AND ms.mobile_phone_id = '%d';", mobilePhoneID
                )
            );
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                store.add(
                    new Store(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
        return store;
    }
}
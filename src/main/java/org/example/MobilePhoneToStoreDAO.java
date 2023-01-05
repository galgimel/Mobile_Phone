package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MobilePhoneToStoreDAO {
    ConnectionFactory connectionFactory;

    public MobilePhoneToStoreDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void deleteMobilePhoneFromAllStores(int mobilePhoneID) {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format(
                    "DELETE FROM mobile_phone_to_store\n" +
                    "WHERE mobile_phone_id = '%d';", mobilePhoneID
                )
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public void deleteMobilePhoneFromStore(int storeID, int mobilePhoneID) {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format(
                    "DELETE FROM mobile_phone_to_store\n" +
                    "WHERE store_id = '%d' AND mobile_phone_id = '%d';", storeID, mobilePhoneID
                )
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }

    public void addMobilePhoneToStore(int storeID, int mobilePhoneID) {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                String.format(
                    "INSERT INTO mobile_phone_to_store(store_id, mobile_phone_id) \n" +
                    "VALUES (%d, %d);", storeID, mobilePhoneID
                )
            );
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }
}

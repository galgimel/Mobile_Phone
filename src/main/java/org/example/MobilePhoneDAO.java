package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilePhoneDAO {
    ConnectionFactory connectionFactory = new ConnectionFactory();

    public void save(MobilePhone mobilePhone) throws SQLException {
        Connection connection = connectionFactory.Connecting();
        PreparedStatement statement = connection.prepareStatement(
            String.format("INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('%s', '%s', %d, %d);",
                mobilePhone.brand, mobilePhone.model, mobilePhone.performance, mobilePhone.price)
        );
        statement.execute();
        connection.close();
    }

    public void delete(String brand, String model) throws SQLException {
        Connection connection = connectionFactory.Connecting();
        PreparedStatement statement = connection.prepareStatement(
            String.format("DELETE FROM mobile_phones WHERE brand = '%s' AND model = '%s';", brand, model)
        );
        statement.execute();
        connection.close();
    }

    public void drop() throws SQLException {
        Connection connection = connectionFactory.Connecting();
        PreparedStatement statement = connection.prepareStatement(
            "DROP TABLE IF EXISTS mobile_phones CASCADE;"
        );
        statement.execute();
        connection.close();
    }

    public void create() throws SQLException {
        Connection connection = connectionFactory.Connecting();
        PreparedStatement statement = connection.prepareStatement(
            "CREATE TABLE mobile_phones (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    brand VARCHAR(64),\n" +
                "    model  VARCHAR(64),\n" +
                "    performance INT,\n" +
                "    price INT\n" +
                ");"
        );
        statement.execute();
        connection.close();
    }

    public void createBase() throws SQLException {
        Connection connection = connectionFactory.Connecting();
        PreparedStatement statement = connection.prepareStatement(
            String.format(
                "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', '14PRO', 11, 1500);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', '14', 10, 1400);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', '12PRO', 10, 1300);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', '12', 9, 1200);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('iPhone', 'R', 8, 1000);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy Note21', 11, 1500);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy S21+', 10, 1350);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy S21', 10, 1200);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy S20+', 9, 1100);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('SAMSUNG', 'Galaxy S20', 9, 1000);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Xiaomi', '12PRO', 10, 1300);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Huawei', 'Xs2', 11, 1700);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Huawei', 'P50 Pocket', 11, 1600);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Realme', 'GT2 PRO12', 9, 1200);\n" +
                    "INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('Nokia', '5310DS', 2, 150);"
            )
        );
        statement.execute();
        connection.close();
    }

    public List<MobilePhone> findAll() throws SQLException {
        Connection connection = connectionFactory.Connecting();
        PreparedStatement statement = connection.prepareStatement(
            String.format("SELECT * FROM mobile_phones;")
        );
        ResultSet resultSet = statement.executeQuery();

        List<MobilePhone> mobilePhone = new ArrayList<>();

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
        return mobilePhone;
    }

    public MobilePhone findByPrice(int price) throws SQLException {
        Connection connection = connectionFactory.Connecting();
        PreparedStatement statement = connection.prepareStatement(
            String.format("SELECT * FROM mobile_phones WHERE price > %d;", price)
        );
        ResultSet resultSet = statement.executeQuery();

        MobilePhone mobilePhone = null;

        while (resultSet.next()) {
            mobilePhone = new MobilePhone(
                resultSet.getInt("id"),
                resultSet.getString("brand"),
                resultSet.getString("model"),
                resultSet.getInt("performance"),
                resultSet.getInt("price")
            );
        }
        return mobilePhone;
    }

    public MobilePhone findByPerformance(int performance) throws SQLException {
        Connection connection = connectionFactory.Connecting();
        PreparedStatement statement = connection.prepareStatement(
            String.format("SELECT * FROM mobile_phones WHERE performance > %d;", performance)
        );
        ResultSet resultSet = statement.executeQuery();

        MobilePhone mobilePhone = null;

        while (resultSet.next()) {
            mobilePhone = new MobilePhone(
                resultSet.getInt("id"),
                resultSet.getString("brand"),
                resultSet.getString("model"),
                resultSet.getInt("performance"),
                resultSet.getInt("price")
            );
        }
        return mobilePhone;
    }
}

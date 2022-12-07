package org.example;

import java.sql.*;

public class MobilePhoneDAO {
    private String user = "postgres";
    private String password = "1234";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String driver = "org.postgresql.Driver";

    public void save(MobilePhone mobilePhone) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(
                String.format("INSERT INTO mobile_phones (brand, model, performance, price) VALUES ('%s', '%s', %d, %d);",
                        mobilePhone.brand, mobilePhone.model, mobilePhone.performance, mobilePhone.price)
        );
        statement.execute();
    }

    public void delete(String brand, String model) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(
                String.format("DELETE FROM mobile_phones WHERE brand = '%s' AND model = '%s';", brand, model)
        );
        statement.execute();
    }

    public void drop() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(
                String.format("DROP TABLE IF EXISTS mobile_phones CASCADE;")
        );
        statement.execute();
    }

    public void create() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(
                String.format("CREATE TABLE mobile_phones (\n" +
                        "    id SERIAL PRIMARY KEY,\n" +
                        "    brand VARCHAR(64),\n" +
                        "    model  VARCHAR(64),\n" +
                        "    performance INT,\n" +
                        "    price INT\n" +
                        ");")
        );
        statement.execute();
    }

    public MobilePhone findByPrice(int price) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM mobile_phones WHERE price > %d;", price)
        );
        ResultSet resultSet = statement.executeQuery();

        MobilePhone mobilePhone = null;

        while(resultSet.next()) {
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

    public MobilePhone findByPerformance(int performance) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * FROM mobile_phones WHERE performance > %d;", performance)
        );
        ResultSet resultSet = statement.executeQuery();

        MobilePhone mobilePhone = null;

        while(resultSet.next()) {
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

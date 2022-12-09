package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "1234");
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty("driver", "org.postgresql.Driver");
        FileOutputStream output = new FileOutputStream("src/main/resources/my.properties");
        properties.store(output, "com");


        MobilePhoneDAO dao = new MobilePhoneDAO();
        dao.drop();
        dao.create();
        dao.createBase();

        System.out.println("1) Вывод всех телефонов выше определенной производительности. \n" +
            "2) Вывод всех телефонов выше определенной цены. \n" +
            "3) Удаление телефона из базы по названию модели и названию компании \n" +
            "4) Добавление нового телефона.");
    }
}
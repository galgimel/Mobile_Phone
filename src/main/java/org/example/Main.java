package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MobilePhoneDAO dao = new MobilePhoneDAO();
        dao.drop();
        dao.create();
        dao.save(new MobilePhone("iPhone", "14PRO", 11, 1500));
        dao.save(new MobilePhone("iPhone", "14", 10, 1400));
        dao.save(new MobilePhone("iPhone", "12PRO", 10, 1300));
        dao.save(new MobilePhone("iPhone", "12", 9, 1200));
        dao.save(new MobilePhone("iPhone", "R", 8, 1000));
        dao.save(new MobilePhone("SAMSUNG", "Galaxy Note21", 11, 1500));
        dao.save(new MobilePhone("SAMSUNG", "Galaxy S21+", 10, 1350));
        dao.save(new MobilePhone("SAMSUNG", "Galaxy S21", 10, 1200));
        dao.save(new MobilePhone("SAMSUNG", "Galaxy S20+", 9, 1100));
        dao.save(new MobilePhone("SAMSUNG", "Galaxy S20", 9, 1000));
        dao.save(new MobilePhone("Xiaomi", "12PRO", 10, 1300));
        dao.save(new MobilePhone("Huawei", "Xs2", 11, 1700));
        dao.save(new MobilePhone("Huawei", "P50 Pocket", 11, 1600));
        dao.save(new MobilePhone("Realme", "GT2 PRO12", 9, 1200));
        dao.save(new MobilePhone("Nokia", "5310DS", 2, 150));

        System.out.println("1) Вывод всех телефонов выше определенной производительности. \n" +
                "2) Вывод всех телефонов выше определенной цены. \n" +
                "3) Удаление телефона из базы по названию модели и названию компании \n" +
                "4) Добавление нового телефона.");
    }
}
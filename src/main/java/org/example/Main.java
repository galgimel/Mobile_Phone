package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.example.Constance.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        FileInputStream in = new FileInputStream(WAY_PROPERTY);
        Properties properties = new Properties();
        properties.load(in);
        in.close();
        ConnectionFactory connectionFactory = new ConnectionFactory(properties);
        MobilePhoneDAO mpDAO = new MobilePhoneDAO(connectionFactory);
        PhoneUserDAO puDAO = new PhoneUserDAO(connectionFactory);
        mpDAO.createBase(createDataBase());

        System.out.println(MENU);
        String query = scanner.nextLine();
        while (!query.equals(END_CHAR)) {
            switch (query) {
                case "1":
                    outputPerformance(mpDAO);
                    break;
                case "2":
                    outputPrice(mpDAO);
                    break;
                case "3":
                    deleteByBrandAndModel(mpDAO, puDAO);
                    break;
                case "4":
                    addPhone(mpDAO);
                    break;
                case "5":
                    outputBrandUsers(puDAO);
                    break;
                case "6":
                    outputNonePhoneUsers(puDAO);
                    break;
                default:
                    System.out.println(DEFAULT + "\n" + MENU);
                    break;
            }
            query = scanner.next();
        }
        System.out.println(DONE);
    }

    static Scanner scanner = new Scanner(System.in);

    private static String createDataBase() {
        List<String> createBaseArray = null;
        try {
            createBaseArray = Files.readAllLines(Path.of(WAY_SQL));
        } catch (IOException e) {
            System.out.println("Проблема! Проверь файл создания базы SQL.");
            ;
        }
        StringBuilder createBaseSB = new StringBuilder();
        for (String string : createBaseArray) {
            createBaseSB.append(string);
        }
        return createBaseSB.toString();
    }

    private static void outputPerformance(MobilePhoneDAO mpDAO) {
        System.out.println(MIN_PERFORMANCE);
        int number1 = scanner.nextInt();
        if (number1 > 1 && number1 < 20) {
            System.out.println(
                READY + "\n" +
                    mpDAO.findByPerformance(number1) + "\n" +
                    MENU);
        } else {
            System.out.println(ERR + "\n" + MENU);
        }
    }

    private static void outputPrice(MobilePhoneDAO mpDAO) {
        System.out.println(MIN_PRICE);
        int number2 = scanner.nextInt();
        if (number2 > 0) {
            System.out.println(READY + "\n" + mpDAO.findByPrice(number2) + "\n" + MENU);
        } else {
            System.out.println(ERR + "\n" + MENU);
        }
    }

    private static void deleteByBrandAndModel(MobilePhoneDAO mpDAO, PhoneUserDAO puDAO) {
        System.out.println(mpDAO.findAll() + "\n" + BRAND);
        String brand = scanner.next();
        System.out.println(MODEL);
        String model = scanner.next();
        puDAO.setMobilePhoneIDNull(mpDAO.findIDByBrandAndModel(brand, model));
        mpDAO.delete(brand, model);
        System.out.println(READY + "\n" + mpDAO.findAll() + "\n" + MENU);
    }

    private static void addPhone(MobilePhoneDAO mpDAO) {
        System.out.println(BRAND);
        String brand = scanner.next();
        System.out.println(MODEL);
        String model = scanner.next();
        System.out.println(PERFORMANCE);
        int performance = scanner.nextInt();
        System.out.println(PRICE);
        int price = scanner.nextInt();
        MobilePhone mobilePhone = new MobilePhone(brand, model, performance, price);
        mpDAO.save(mobilePhone);
        System.out.println(READY + "\n" + mpDAO.findAll() + "\n" + MENU);
    }

    private static void outputBrandUsers(PhoneUserDAO puDAO) {
        System.out.println(BRAND);
        String brand = scanner.next();
        try {
            System.out.println(READY + "\n" + puDAO.findBrandUsers(brand) + "\n" + MENU);
        } catch (Exception e) {
            System.out.println(ERR + "\n" + MENU);
        }
    }

    private static void outputNonePhoneUsers(PhoneUserDAO puDAO) {
        try {
            System.out.println(READY + "\n" + puDAO.findNoneUsers() + "\n" + MENU);
        } catch (Exception e) {
            System.out.println(CASE_6_ERR + "\n" + MENU);
        }
    }
}
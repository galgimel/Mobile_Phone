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

        List<String> createBaseArray = Files.readAllLines(Path.of(WAY_SQL));
        StringBuilder createBaseSB = new StringBuilder();
        for (String string : createBaseArray) {
            createBaseSB.append(string);
        }

        MobilePhoneDAO mpDAO = new MobilePhoneDAO(connectionFactory);
        PhoneUserDAO puDAO = new PhoneUserDAO(connectionFactory);
        mpDAO.createBase(createBaseSB.toString());

        System.out.println(MENU);
        System.out.println(mpDAO.findAll());
        String query = scanner.nextLine();
        while (!query.equals(END_CHAR)) {
            switch (query) {
                case "1":
                    System.out.println(MIN_PERFORMANCE);
                    int number1 = scanner.nextInt();
                    if (number1 < 1 || number1 > 20) {
                        System.out.println(READY + "\n" + mpDAO.findByPerformance(number1));
                    } else {
                        System.out.println(ERR + "\n" + MENU);
                    }
                    break;
                case "2":
                    System.out.println(MIN_PRICE);
                    int number2 = scanner.nextInt();
                    if (number2 < 0) {
                        System.out.println(READY + "\n" + mpDAO.findByPrice(number2));
                    } else {
                        System.out.println(ERR + "\n" + MENU);
                    }
                    break;
                case "3":
                    System.out.println(CASE_3);
                    String array3 = scanner.next();
                    List<String> options3 = List.of(array3.split(","));
                    if (options3.size() == 2){
                        mpDAO.delete(options3.get(0), options3.get(1));
                        System.out.println(READY + "\n" + mpDAO.findAll());
                    } else {
                        System.out.println(ERR + "\n" + MENU);
                    }
                    break;
                case "4":
                    System.out.println(CASE_4);
                    String array4 = scanner.next();
                    List<String> options4 = List.of(array4.split(","));
                    if (options4.size() == 4){
                        MobilePhone mobilePhone = new MobilePhone(
                            options4.get(0),
                            options4.get(1),
                            Integer.parseInt(options4.get(2)),
                            Integer.parseInt(options4.get(3)));
                        mpDAO.save(mobilePhone);
                        System.out.println(READY + "\n" + mpDAO.findAll());
                    } else {
                        System.out.println(ERR + "\n" + MENU);
                    }
                    break;
                case "5" :
                    System.out.println(CASE_5);
                    String brand = scanner.next();
                    try {
                        System.out.println(READY + "\n" + puDAO.findBrandUsers(brand));
                    } catch (Exception e) {
                        System.out.println(ERR + "\n" + MENU);
                    }
                    break;
                case "6" :
                    try {
                        System.out.println(READY + "\n" + puDAO.findNoneUsers());
                    } catch (Exception e) {
                        System.out.println(CASE_6_ERR + "\n" + MENU);
                    }
                    break;
                default:
                    System.out.println(DEFAULT + "\n" + MENU);
                    break;
            }
            query = scanner.next();
        }
        System.out.println(DONE + "\n" + mpDAO.findAll());
    }
}
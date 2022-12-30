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
        Cases cases = new Cases();

        System.out.println(MENU);
        System.out.println(mpDAO.findAll());
        String query = scanner.nextLine();
        while (!query.equals(END_CHAR)) {
            switch (query) {
                case "1":
                    cases.case1(mpDAO);
                    break;
                case "2":
                    cases.case2(mpDAO);
                    break;
                case "3":
                    cases.case3(mpDAO);
                    break;
                case "4":
                    cases.case4(mpDAO);
                    break;
                case "5":
                    cases.case5(puDAO);
                    break;
                case "6":
                    cases.case6(puDAO);
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
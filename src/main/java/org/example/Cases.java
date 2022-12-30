package org.example;

import java.util.List;
import java.util.Scanner;

import static org.example.Constance.*;

public class Cases {
    Scanner scanner = new Scanner(System.in);

    void case1(MobilePhoneDAO mpDAO) {
        System.out.println(MIN_PERFORMANCE);
        int number1 = scanner.nextInt();
        if (number1 > 1 && number1 < 20) {
            System.out.println(READY + "\n" + mpDAO.findByPerformance(number1));
        } else {
            System.out.println(ERR + "\n" + MENU);
        }
    }

    void case2(MobilePhoneDAO mpDAO) {
        System.out.println(MIN_PRICE);
        int number2 = scanner.nextInt();
        if (number2 > 0) {
            System.out.println(READY + "\n" + mpDAO.findByPrice(number2));
        } else {
            System.out.println(ERR + "\n" + MENU);
        }
    }

    void case3(MobilePhoneDAO mpDAO) {
        System.out.println(CASE_3);
        String array3 = scanner.next();
        List<String> options3 = List.of(array3.split(","));
        if (options3.size() == 2) {
            mpDAO.delete(options3.get(0), options3.get(1));
            System.out.println(READY + "\n" + mpDAO.findAll());
        } else {
            System.out.println(ERR + "\n" + MENU);
        }
    }

    void case4(MobilePhoneDAO mpDAO) {
        System.out.println(CASE_4);
        String array4 = scanner.next();
        List<String> options4 = List.of(array4.split(","));
        if (options4.size() == 4) {
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
    }

    void case5(PhoneUserDAO puDAO) {
        System.out.println(CASE_5);
        String brand = scanner.next();
        try {
            System.out.println(READY + "\n" + puDAO.findBrandUsers(brand));
        } catch (Exception e) {
            System.out.println(ERR + "\n" + MENU);
        }
    }

    void case6(PhoneUserDAO puDAO) {
        try {
            System.out.println(READY + "\n" + puDAO.findNoneUsers());
        } catch (Exception e) {
            System.out.println(CASE_6_ERR + "\n" + MENU);
        }
    }
}
package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Stream;

import static org.example.Main.createDataBase;
import static org.junit.jupiter.api.Assertions.*;

class MobilePhoneDAOTest {
    Properties properties;
    ConnectionFactory connectionFactory;
    MobilePhoneDAO mpDAO;
    PhoneUserDAO puDAO;
    MobilePhoneToStoreDAO mpsDAO;
    public MobilePhoneDAOTest() throws IOException {
        properties = new Properties();
        FileInputStream in = new FileInputStream("src/test/resources/test.properties");
        properties.load(in);
        in.close();
        connectionFactory = new ConnectionFactory(properties);
        mpDAO = new MobilePhoneDAO(connectionFactory);
        puDAO = new PhoneUserDAO(connectionFactory);
        mpsDAO = new MobilePhoneToStoreDAO(connectionFactory);
    }

    @BeforeEach
    void init() {
        mpDAO.createBase(createDataBase("src/test/resources/initTest.sql"));
    }

    @Test
    void save() {
        mpDAO.save(new MobilePhone("Nokia", "3190", 3, 300));
        String actual = mpDAO.findAll().toString();
        String expected =
            "[MobilePhone{id=1, brand='iPhone', model='14PRO', performance=11, price=1500}, " +
                "MobilePhone{id=2, brand='SAMSUNG', model='Galaxy Note21', performance=11, price=1500}, " +
                "MobilePhone{id=3, brand='Nokia', model='5310DS', performance=2, price=150}, " +
                "MobilePhone{id=4, brand='Nokia', model='3190', performance=3, price=300}]";
        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        puDAO.setMobilePhoneIDNull(1);
        mpsDAO.deleteMobilePhoneFromAllStores(1);
        mpDAO.delete("iPhone", "14PRO");
        String actual = mpDAO.findAll().toString();
        String expected =
            "[MobilePhone{id=2, brand='SAMSUNG', model='Galaxy Note21', performance=11, price=1500}, " +
                "MobilePhone{id=3, brand='Nokia', model='5310DS', performance=2, price=150}]";
        assertEquals(expected, actual);
    }

    @Test
    void createBase() {
        String actual = mpDAO.findAll().toString();
        String expected =
            "[MobilePhone{id=1, brand='iPhone', model='14PRO', performance=11, price=1500}, " +
                "MobilePhone{id=2, brand='SAMSUNG', model='Galaxy Note21', performance=11, price=1500}, " +
                "MobilePhone{id=3, brand='Nokia', model='5310DS', performance=2, price=150}]";
        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        String actual = mpDAO.findAll().toString();
        String expected =
            "[MobilePhone{id=1, brand='iPhone', model='14PRO', performance=11, price=1500}, " +
                "MobilePhone{id=2, brand='SAMSUNG', model='Galaxy Note21', performance=11, price=1500}, " +
                "MobilePhone{id=3, brand='Nokia', model='5310DS', performance=2, price=150}]";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("expectedAnswer5")
    void findByPrice(String expected, int price) {
        String actual = mpDAO.findByPrice(price).toString();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> expectedAnswer5() {
        return Stream.of(
            Arguments.of(
                "[MobilePhone{id=1, brand='iPhone', model='14PRO', performance=11, price=1500}, " +
                    "MobilePhone{id=2, brand='SAMSUNG', model='Galaxy Note21', performance=11, price=1500}]",
                1400),
            Arguments.of(
                "[MobilePhone{id=1, brand='iPhone', model='14PRO', performance=11, price=1500}, " +
                    "MobilePhone{id=2, brand='SAMSUNG', model='Galaxy Note21', performance=11, price=1500}, " +
                    "MobilePhone{id=3, brand='Nokia', model='5310DS', performance=2, price=150}]",
                100)
        );
    }

    @ParameterizedTest
    @MethodSource("expectedAnswer6")
    void findByPerformance(String expected, int performance) {
        String actual = mpDAO.findByPerformance(performance).toString();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> expectedAnswer6() {
        return Stream.of(
            Arguments.of(
                "[MobilePhone{id=1, brand='iPhone', model='14PRO', performance=11, price=1500}, " +
                    "MobilePhone{id=2, brand='SAMSUNG', model='Galaxy Note21', performance=11, price=1500}]",
                10),
            Arguments.of(
                "[MobilePhone{id=1, brand='iPhone', model='14PRO', performance=11, price=1500}, " +
                    "MobilePhone{id=2, brand='SAMSUNG', model='Galaxy Note21', performance=11, price=1500}, " +
                    "MobilePhone{id=3, brand='Nokia', model='5310DS', performance=2, price=150}]",
                1)
        );
    }

    @ParameterizedTest
    @MethodSource("expectedAnswer7")
    void findPhonesByStore(String expected, int store_id) {
        String actual = mpDAO.findPhonesByStore(store_id).toString();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> expectedAnswer7() {
        return Stream.of(
            Arguments.of(
                "[MobilePhone{id=0, brand='iPhone', model='14PRO', performance=0, price=0}]", 1),
            Arguments.of(
                "[MobilePhone{id=0, brand='SAMSUNG', model='Galaxy Note21', performance=0, price=0}]", 2),
            Arguments.of(
                "[MobilePhone{id=0, brand='Nokia', model='5310DS', performance=0, price=0}]", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("expectedAnswer8")
    void findIDByBrandAndModel(int expected, String brand, String model) {
        int actual = mpDAO.findIDByBrandAndModel(brand, model);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> expectedAnswer8() {
        return Stream.of(
            Arguments.of(1, "iPhone", "14PRO"),
            Arguments.of(2, "SAMSUNG", "Galaxy Note21"),
            Arguments.of(3, "Nokia", "5310DS")
        );
    }
}
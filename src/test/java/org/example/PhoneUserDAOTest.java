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

import static org.example.Constance.*;
import static org.example.Main.createDataBase;
import static org.junit.jupiter.api.Assertions.*;

class PhoneUserDAOTest {
    Properties properties;
    ConnectionFactory connectionFactory;
    MobilePhoneDAO mpDAO;
    PhoneUserDAO puDAO;

    public PhoneUserDAOTest() throws IOException {
        properties = new Properties();
        FileInputStream in = new FileInputStream("src/test/resources/test.properties");
        properties.load(in);
        in.close();
        connectionFactory = new ConnectionFactory(properties);
        mpDAO = new MobilePhoneDAO(connectionFactory);
        puDAO = new PhoneUserDAO(connectionFactory);
    }

    @BeforeEach
    void init() {
        mpDAO.createBase(createDataBase("src/test/resources/initTest.sql"));
    }

    @Test
    void findAll() {
        String actual = puDAO.findAll().toString();
        String expected =
            "[PhoneUser{id=1, name='Рюрик В.Д.', mobile_phone=2}, " +
                "PhoneUser{id=2, name='Вовин И.Ж.', mobile_phone=0}, " +
                "PhoneUser{id=3, name='Жириновская Р.Л.', mobile_phone=1}, " +
                "PhoneUser{id=4, name='Гоголь Ю.А.', mobile_phone=3}, " +
                "PhoneUser{id=5, name='Цветкова Д.Д.', mobile_phone=2}]";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("expectedAnswer2")
    void setMobilePhoneIDNull(String expected, int id) {
        puDAO.setMobilePhoneIDNull(id);
        String actual = puDAO.findAll().toString();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> expectedAnswer2() {
        return Stream.of(
            Arguments.of(
                "[PhoneUser{id=1, name='Рюрик В.Д.', mobile_phone=2}, " +
                    "PhoneUser{id=2, name='Вовин И.Ж.', mobile_phone=0}, " +
                    "PhoneUser{id=3, name='Жириновская Р.Л.', mobile_phone=0}, " +
                    "PhoneUser{id=4, name='Гоголь Ю.А.', mobile_phone=3}, " +
                    "PhoneUser{id=5, name='Цветкова Д.Д.', mobile_phone=2}]",
                1),
            Arguments.of(
                "[PhoneUser{id=1, name='Рюрик В.Д.', mobile_phone=0}, " +
                    "PhoneUser{id=2, name='Вовин И.Ж.', mobile_phone=0}, " +
                    "PhoneUser{id=3, name='Жириновская Р.Л.', mobile_phone=1}, " +
                    "PhoneUser{id=4, name='Гоголь Ю.А.', mobile_phone=3}, " +
                    "PhoneUser{id=5, name='Цветкова Д.Д.', mobile_phone=0}]",
                2),
            Arguments.of("[PhoneUser{id=1, name='Рюрик В.Д.', mobile_phone=2}, " +
                    "PhoneUser{id=2, name='Вовин И.Ж.', mobile_phone=0}, " +
                    "PhoneUser{id=3, name='Жириновская Р.Л.', mobile_phone=1}, " +
                    "PhoneUser{id=4, name='Гоголь Ю.А.', mobile_phone=0}, " +
                    "PhoneUser{id=5, name='Цветкова Д.Д.', mobile_phone=2}]",
                3)
        );
    }

    @ParameterizedTest
    @MethodSource("expectedAnswer3")
    void findBrandUsers(String expected, String brand) {
        String actual = String.valueOf(puDAO.findBrandUsers(brand));
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> expectedAnswer3() {
        return Stream.of(
            Arguments.of("1", "iPhone"),
            Arguments.of("2", "SAMSUNG"),
            Arguments.of("1", "Nokia")
        );
    }

    @Test
    void findNoneUsers() {
        int expected = 1;
        int actual = puDAO.findNoneUsers();
        assertEquals(expected, actual);
    }
}
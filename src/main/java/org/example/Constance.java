package org.example;

public class Constance {
    public static final String WAY_PROPERTY = "src/main/resources/my.properties";
    public static final String WAY_SQL = "src/main/resources/init.sql";
    public static final String END_CHAR = "*";
    public static final String READY = "Готово:";
    public static final String DONE = "Работа завершена. Итог:";
    public static final String MIN_PERFORMANCE = "Введите минимальную желаемую производительность.";
    public static final String MIN_PRICE = "Введите минимальную желаемую цену.";
    public static final String CASE_3 = "Введите бренд и модель телефона через запятую без пробелов.";
    public static final String CASE_4 = "Введите бренд, модель, производительность и цену телефона в одну строку через запятую без пробелов.";
    public static final String CASE_5 = "Введите бренд.";
    public static final String CASE_5_ERR = "Кажется такого бренда нет :( \n Попробуй еще раз.";
    public static final String CASE_6_ERR = "Ой-ей, кажется что то пошло не так, либо все юзеры приобрели телефон! \n Проверь базу данных :)";
    public static final String DEFAULT = "Введите корректную цифру.";
    public static final String ERR = "Данные введены не корректно. Попробуйте снова.";
    public static final String MENU = "Введите цифру: \n" +
        "* - Завершение работы.\n" +
        "1 - Вывод всех телефонов выше определенной производительности. \n" +
        "2 - Вывод всех телефонов выше определенной цены. \n" +
        "3 - Удаление телефона из базы по названию модели и названию компании. \n" +
        "4 - Добавление нового телефона. \n" +
        "5 - Вывод пользователей телефона одного бренда. \n" +
        "6 - Вывод пользователей без телефона.";
}
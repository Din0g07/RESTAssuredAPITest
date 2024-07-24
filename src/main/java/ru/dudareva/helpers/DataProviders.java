package ru.dudareva.helpers;

import org.testng.annotations.DataProvider;

/**
 * Класс, содержащий методы для предоставления данных тестам.
 *
 * @version 1.0
 * @author Диана Дударева
 */
public class DataProviders {

    /**
     * DataProvider для успешного логина.
     *
     * @return двумерный массив объектов с данными для успешного логина
     */
    @DataProvider
    public static Object[][] successfulLogin() {
        return new Object[][] {{"eve.holt@reqres.in", "cityslicka"}};
    }

    /**
     * DataProvider для неуспешного логина.
     *
     * @return двумерный массив объектов с данными для неуспешного логина
     */
    @DataProvider
    public static Object[][] failedLogin() {
        return new Object[][] {{"eve.holt@reqres.in"}};
    }

    /**
     * DataProvider для проверки количества тегов.
     *
     * @return двумерный массив объектов с данными для проверки количества тегов
     */
    @DataProvider
    public Object[][] countTags() {
        return new Object[][]{{15}};
    }
}

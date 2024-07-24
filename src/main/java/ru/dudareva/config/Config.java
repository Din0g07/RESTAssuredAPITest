package ru.dudareva.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для загрузки переменных окружения из файла config.properties.
 *
 * @version 1.0
 * @author Диана Дударева
 */
public class Config {

    /**
     * Переменные окружения.
     */
    private static Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Возвращает значение переменной окружения по указанному ключу.
     *
     * @param key ключ свойства
     * @return значение свойства
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}


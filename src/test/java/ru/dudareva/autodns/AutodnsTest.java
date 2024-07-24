package ru.dudareva.autodns;

import io.qameta.allure.Feature;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.dudareva.config.Config;
import ru.dudareva.helpers.DataProviders;

import static io.restassured.RestAssured.given;

/**
 * Класс для тестирования сервиса Autodns.
 * Содержит тесты для проверки ответа сервиса на количество тегов в нем.
 *
 * @version 1.0
 * @author Диана Дударева
 */
public class AutodnsTest {

    /**
     * Тест для проверки количества тегов в ответе от сервиса Autodns.
     * Отправляет запрос на базовый URI, указанный в конфигурации, и проверяет, что количество тегов
     * в ответе совпадает с ожидаемым значением, переданным через DataProvider.
     *
     * @param count ожидаемое количество тегов в ответе
     */
    @Feature("Проверка колличества тэгов в ответе")
    @Test(dataProvider = "countTags", dataProviderClass = DataProviders.class,
            testName = "Проверка колличества тэгов в ответе")
    public void countTags(int count) {
        String responseBody = given()
                .when()
                .get(Config.getProperty("autodns.baseUri"))
                .then()
                .log().body()
                .extract().body().asString();

        Document doc = Jsoup.parse(responseBody, "", org.jsoup.parser.Parser.xmlParser());
        int tagCount = doc.getAllElements().size();
        Assert.assertEquals(tagCount, count, "Количество тегов в ответе должно быть равно " + count);
    }
}

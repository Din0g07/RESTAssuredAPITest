package ru.dudareva.reqres.specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.dudareva.config.Config;

/**
 * Класс для настройки спецификаций запросов и ответов в RestAssured.
 *
 * @version 1.0
 * @author Диана Дударева
 */
public class Specification {

    /**
     * Создает и возвращает спецификацию запроса.
     *
     * @return объект RequestSpecification с базовым URI и типом контента
     */
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.getProperty("reqres.baseUri"))
                .setContentType(Config.getProperty("application_type"))
                .build();
    }

    /**
     * Создает и возвращает спецификацию ответа с кодом 200.
     *
     * @return объект ResponseSpecification с ожидаемым статусом 200
     */
    public static ResponseSpecification responseSpec200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    /**
     * Создает и возвращает спецификацию ответа с кодом 400.
     *
     * @return объект ResponseSpecification с ожидаемым статусом 400
     */
    public static ResponseSpecification responseSpec400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    /**
     * Устанавливает спецификации запроса и ответа в RestAssured.
     *
     * @param requestSpec спецификация запроса
     * @param responseSpec спецификация ответа
     */
    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Удаляет текущие спецификации запроса и ответа в RestAssured.
     */
    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }

}

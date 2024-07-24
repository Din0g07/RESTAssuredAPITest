package ru.dudareva.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dudareva.reqres.dto.DataResponseDTO;
import ru.dudareva.reqres.dto.LoginRq;
import ru.dudareva.reqres.dto.LoginRs;

import java.io.IOException;

import static io.restassured.RestAssured.given;

/**
 * Класс, содержащий вспомогательные методы для тестов.
 *
 * @version 1.0
 * @author Диана Дударева
 */
public class TestHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Метод для отправки POST запроса на логин.
     *
     * @param loginRq объект запроса логина
     * @return объект ответа логина
     */
    public static LoginRs postLogin(LoginRq loginRq) {
        return given()
                .body(loginRq)
                .when()
                .post("/api/login")
                .then()
                .log().body()
                .extract().body().as(LoginRs.class);
    }

    /**
     * Метод для отправки GET запроса и маппинга ответа в указанный тип.
     *
     * @param url URL для отправки запроса
     * @param typeReference ссылка на тип для маппинга ответа
     * @param <T> тип данных в ответе
     * @return объект DataResponseDTO с данными указанного типа
     */
    public static <T> DataResponseDTO<T> getApiResponseAndMap(
            String url, TypeReference<DataResponseDTO<T>> typeReference) {

        String responseBody = given()
                .when()
                .get(url)
                .then()
                .log().body()
                .extract().body().asString();

        DataResponseDTO<T> dataResponseDTO;
        try {
            dataResponseDTO = objectMapper
                    .readValue(responseBody, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return dataResponseDTO;
    }
}

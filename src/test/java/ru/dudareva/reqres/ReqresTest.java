package ru.dudareva.reqres;

import com.fasterxml.jackson.core.type.TypeReference;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ru.dudareva.reqres.dto.DataResourceDTO;
import ru.dudareva.reqres.dto.DataResponseDTO;
import ru.dudareva.reqres.dto.DataUserDTO;
import ru.dudareva.reqres.dto.LoginRq;
import ru.dudareva.reqres.dto.LoginRs;
import ru.dudareva.helpers.DataProviders;
import ru.dudareva.helpers.TestHelper;
import ru.dudareva.reqres.specification.Specification;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для тестирования сервиса Reqres.
 * Содержит тесты для проверки различных функциональностей сервиса.
 *
 * @version 1.0
 * @author Диана Дударева
 */
public class ReqresTest {

    /**
     * Устанавливает спецификацию для ответов с кодом 200 перед выполнением тестов группы "200".
     */
    @BeforeGroups(value = "200")
    public void setupSpec200() {
        Specification.installSpec(Specification.requestSpec(),
                Specification.responseSpec200());
    }

    /**
     * Устанавливает спецификацию для ответов с кодом 400 перед выполнением тестов группы "400".
     */
    @BeforeGroups(value = "400")
    public void setupSpec400() {
        Specification.installSpec(Specification.requestSpec(),
                Specification.responseSpec400());
    }

    /**
     * Очищает спецификации после выполнения всех тестов.
     */
    @AfterClass
    public void clearSpec() {
        Specification.deleteSpec();
    }

    /**
     * Тест для проверки уникальности аватаров в списке пользователей.
     * Отправляет запрос на получение списка пользователей и проверяет, что все аватары уникальны.
     */
    @Feature("Проверка на уникальность аватаров в списке")
    @Test(groups = {"200"}, testName = "Проверка на уникальность аватаров в списке")
    public void uniqueAvatarsFromUserList() {
        TypeReference<DataResponseDTO<DataUserDTO>> typeRef = new TypeReference<>() {};
        DataResponseDTO<DataUserDTO> dataResponseDTO =
                TestHelper.getApiResponseAndMap("/api/users?page=2", typeRef);

        List<String> avatars = dataResponseDTO.getData()
                .stream()
                .map(DataUserDTO::getAvatar)
                .distinct().toList();

        Assert.assertEquals(avatars.size(),
                dataResponseDTO.getData().size(), "Файлы аватаров должны быть уникальными");

    }

    /**
     * Тест для проверки успешного логина.
     * Отправляет запрос на логин с корректными данными и проверяет, что токен присутствует, а ошибки нет.
     *
     * @param email    адрес электронной почты пользователя
     * @param password пароль пользователя
     */
    @Feature("Проверка успешного логина")
    @Test(groups = {"200"}, dataProvider = "successfulLogin", dataProviderClass = DataProviders.class,
            testName = "Проверка успешного логина")
    public void successfulLogin(String email, String password) {
        LoginRq loginRq = new LoginRq(email, password);

        LoginRs loginRs = TestHelper.postLogin(loginRq);

        Assert.assertNotNull(loginRs.getToken(), "Токен должен присутствовать");
        Assert.assertNull(loginRs.getError(), "Ошибка должна отсутствовать");
    }

    /**
     * Тест для проверки неуспешного логина.
     * Отправляет запрос на логин с некорректными данными и проверяет, что ошибка присутствует, а токен отсутствует.
     *
     * @param email адрес электронной почты пользователя
     */
    @Feature("Проверка неуспешного логина")
    @Test(groups = {"400"}, dataProvider = "failedLogin", dataProviderClass = DataProviders.class,
            testName = "Проверка неуспешного логина")
    public void failedLogin(String email) {
        LoginRq loginRq = new LoginRq(email, null);

        LoginRs loginRs = TestHelper.postLogin(loginRq);

        Assert.assertNotNull(loginRs.getError(), "Ошибка должна присутствовать");
        Assert.assertNull(loginRs.getToken(), "Токен должен отсутствовать");
    }

    /**
     * Тест для проверки сортировки ресурсов по году.
     * Отправляет запрос на получение списка ресурсов и проверяет, что ресурсы отсортированы по году.
     */
    @Feature("Проверка сортировки ресурсов по году")
    @Test(groups = {"200"}, testName = "Проверка сортировки ресурсов по году")
    public void sortResourcesByYear() {
        TypeReference<DataResponseDTO<DataResourceDTO>> typeRef = new TypeReference<>() {};
        DataResponseDTO<DataResourceDTO> dataResponseDTO =
                TestHelper.getApiResponseAndMap("/api/unknown", typeRef);

        List<DataResourceDTO> resources = dataResponseDTO.getData();

        List<DataResourceDTO> sortedResources = resources.stream()
                .sorted(Comparator.comparing(DataResourceDTO::getYear))
                .collect(Collectors.toList());

        Assert.assertEquals(resources, sortedResources, "Ресурсы должны быть отсортированы по году");
    }

}

package ru.dudareva.reqres.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, представляющий данные пользователя.
 *
 * @version 1.0
 * @autor Диана Дударева
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DataUserDTO {

    /**
     * Идентификатор пользователя.
     */
    private Integer id;

    /**
     * Электронная почта пользователя.
     */
    private String email;

    /**
     * Имя пользователя.
     */
    @JsonAlias(value = "first_name")
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    @JsonAlias(value = "last_name")
    private String lastName;

    /**
     * URL аватара пользователя.
     */
    private String avatar;
}

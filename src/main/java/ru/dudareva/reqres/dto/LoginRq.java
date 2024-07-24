package ru.dudareva.reqres.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, представляющий входные параметры для логина.
 *
 * @version 1.0
 * @autor Диана Дударева
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRq {

    /**
     * Электроная почта пользователя.
     */
    String email;

    /**
     * Пароль пользователя.
     */
    String password;
}

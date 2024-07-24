package ru.dudareva.reqres.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, представляющий ответ метода логин.
 *
 * @version 1.0
 * @autor Диана Дударева
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRs {

    /**
     * Уникальный токен пользователя.
     */
    String token;

    /**
     * Ошибка при попытке логина.
     */
    String error;
}

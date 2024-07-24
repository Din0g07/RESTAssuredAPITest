package ru.dudareva.reqres.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, представляющий часть ответа с системными данными.
 *
 * @version 1.0
 * @autor Диана Дударева
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SupportDTO {

    /**
     * Системная ссылка.
     */
    private String url;

    /**
     * Системный текст.
     */
    private String text;
}

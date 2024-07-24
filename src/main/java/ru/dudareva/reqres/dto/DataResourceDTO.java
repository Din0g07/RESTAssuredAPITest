package ru.dudareva.reqres.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, представляющий данные ресурса.
 *
 * @version 1.0
 * @author Диана Дударева
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DataResourceDTO {

    /**
     * Идентификатор ресурса.
     */
    private Integer id;

    /**
     * Имя ресурса.
     */
    private String name;

    /**
     * Год ресурса.
     */
    private Integer year;

    /**
     * Цвет ресурса.
     */
    private String color;

    /**
     * Значение Pantone ресурса.
     */
    @JsonAlias(value = "pantone_value")
    private String pantoneValue;
}

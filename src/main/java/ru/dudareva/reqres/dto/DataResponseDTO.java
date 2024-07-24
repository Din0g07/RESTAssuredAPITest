package ru.dudareva.reqres.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/**
 * Класс, представляющий общий ответ с данными.
 *
 * @param <T> тип данных, содержащихся в ответе.
 *
 * @version 1.0
 * @author Диана Дударева
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DataResponseDTO<T> {

    /**
     * Текущая страница.
     */
    private Integer page;

    /**
     * Количество элементов на странице.
     */
    @JsonAlias(value = "per_page")
    private Integer perPage;

    /**
     * Общее количество элементов.
     */
    private Integer total;

    /**
     * Общее количество страниц.
     */
    @JsonAlias(value = "total_pages")
    private Integer totalPages;

    /**
     * Список данных.
     */
    private List<T> data;

    /**
     * Объект поддержки.
     */
    private SupportDTO support;
}

package ru.sklad.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Модель для представления ошибки.
 *
 * @author ChiniakinD.
 */
@Getter
@Setter
@Accessors(chain = true)
public class ApiError {

    private String status;

    private String message;

    private String details;

}

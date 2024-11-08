package ru.sklad.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Модель для редактирования товара.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class ProductEditModel {

    @Schema(name = "name", example = "Автомобиль", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(name = "description", example = "Красивый автомобиль марки BMW", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Schema(name = "price", example = "150", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer price;

    @Schema(name = "isAvailable", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private boolean isAvailable;

}

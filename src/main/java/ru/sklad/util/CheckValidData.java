package ru.sklad.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sklad.exception.BadRequestException;
import ru.sklad.model.ProductEditModel;
import ru.sklad.model.ProductModel;

/**
 * Сервис для проверки корректности поступающих данных по товару.
 *
 * @author ChiniakinD
 */
@Component
@RequiredArgsConstructor
public class CheckValidData {

    public void checkProductModel(ProductModel productModel) {

        if (productModel.getName() == null || productModel.getName().isBlank() || productModel.getName().length() > 255) {
            throw new BadRequestException("Название товара ограничено 255 символами и оно обязательно");
        }

        if (productModel.getDescription() != null && productModel.getDescription().length() > 4096) {
            throw new BadRequestException("Описание товара ограничено 4096 символами");
        }

        if (productModel.getPrice() != null && productModel.getPrice() < 0) {
            throw new BadRequestException("Цена товара не может быть меньше 0");
        }
    }

    public void checkProductEditModel(ProductEditModel productEditModel) {

        if (productEditModel.getName() != null && (productEditModel.getName().isBlank() || productEditModel.getName().length() > 255)) {
            throw new BadRequestException("Название товара ограничено 255 символами и оно обязательно");
        }

        if (productEditModel.getDescription() != null && productEditModel.getDescription().length() > 4096) {
            throw new BadRequestException("Описание товара ограничено 4096 символами");
        }

        if (productEditModel.getPrice() != null && productEditModel.getPrice() < 0) {
            throw new BadRequestException("Цена товара не может быть меньше 0");
        }

    }

}

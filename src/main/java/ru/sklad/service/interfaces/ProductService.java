package ru.sklad.service.interfaces;

import ru.sklad.model.ProductEditModel;
import ru.sklad.model.ProductModel;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса для работы с товарами.
 *
 * @author ChiniakinD
 */
public interface ProductService {

    /**
     * Получает список всех товаров.
     *
     * @return список всех товаров.
     */
    List<ProductModel> getAllProducts();

    /**
     * Получает товар по его id.
     *
     * @param id id товара.
     * @return товар.
     */
    ProductModel getProductById(UUID id);

    /**
     * Добавляет новый товар.
     *
     * @param productModel модель товара.
     */
    void addProduct(ProductModel productModel);

    /**
     * Обновляет товар по его id.
     *
     * @param id               id товара.
     * @param productEditModel модель товара для редактирования.
     */
    void updateProduct(UUID id, ProductEditModel productEditModel);

    /**
     * Удаляет товар по его id.
     *
     * @param id id товара.
     */
    void deleteProduct(UUID id);

}

package ru.sklad.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sklad.exception.NotFoundException;
import ru.sklad.model.ProductEditModel;
import ru.sklad.model.ProductModel;
import ru.sklad.service.interfaces.ProductService;
import ru.sklad.util.CheckValidData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;


/**
 * Реализация сервиса {@link ProductService} для работы с товарами.
 *
 * @author ChiniakinD
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final Map<UUID, ProductModel> products = new HashMap<>();
    private final ModelMapper mergeMapper;
    private final CheckValidData checkValidData;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductModel> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductModel getProductById(UUID id) {
        if (!productIsExist(id)) {
            throw new NotFoundException("Товара с таким id не найдено");
        }
        return products.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProduct(ProductModel productModel) {
        checkValidData.checkProductModel(productModel);
        UUID id = UUID.randomUUID();
        products.put(id, productModel);
        log.info("Товар с id {} создан", id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProduct(UUID id, ProductEditModel productEditModel) {
        checkValidData.checkProductEditModel(productEditModel);
        if (!productIsExist(id)) {
            throw new NotFoundException("Товара с таким id не найдено");
        }

        ProductModel productForUpdate = products.get(id);
        mergeMapper.map(productEditModel, productForUpdate);
        products.put(id, productForUpdate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProduct(UUID id) {
        if (!productIsExist(id)) {
            throw new NotFoundException("Товара с таким id не найдено");
        }
        products.remove(id);
    }

    private boolean productIsExist(UUID id) {
        return products.containsKey(id);
    }

}

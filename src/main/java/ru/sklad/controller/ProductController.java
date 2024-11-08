package ru.sklad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.sklad.model.ApiError;
import ru.sklad.model.ProductEditModel;
import ru.sklad.model.ProductModel;
import ru.sklad.service.interfaces.ProductService;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с товарами.
 *
 * @author ChiniakinD
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Получает все товары.
     *
     * @return список всех товаров.
     */
    @Operation(
            summary = "Получить все товары",
            tags = {"ProductController"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список товаров", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductModel.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    })
            }
    )
    @GetMapping
    public List<ProductModel> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Получает товар по его id.
     *
     * @param id id товара.
     * @return товар.
     */
    @Operation(
            summary = "Получить товар по id",
            tags = {"ProductController"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Товар найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    })
            }
    )
    @GetMapping("/{id}")
    public ProductModel getProductById(@Parameter(name = "id", description = "id товара", required = true, in = ParameterIn.PATH)
                                       @PathVariable("id") UUID id) {
        return productService.getProductById(id);
    }

    /**
     * Создает новый товар. Для удобства в логи возвращает UUID нового товара.
     *
     * @param productModel {@link ProductModel}
     */
    @Operation(
            summary = "Добавить новый товар",
            tags = {"ProductController"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Товар добавлен"),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    })
            }
    )
    @PostMapping
    public void addProduct(@RequestBody ProductModel productModel) {
        productService.addProduct(productModel);
    }

    /**
     * Обновляет товар по его id.
     *
     * @param id               id товара.
     * @param productEditModel {@link ProductEditModel}.
     */
    @Operation(
            summary = "Редактировать товар",
            tags = {"ProductController"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Товар изменен", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Ошибка авторизации", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }),
                    @ApiResponse(responseCode = "403", description = "Нет доступа", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    })
            }
    )
    @PatchMapping("/{id}")
    public void editProduct(@Parameter(name = "id", description = "id товара для редактирования", required = true, in = ParameterIn.PATH) @PathVariable("id") UUID id,
                            @Parameter(name = "ProductModel", description = "модель для редактирования", required = true)
                            @RequestBody ProductEditModel productEditModel) {
        productService.updateProduct(id, productEditModel);
    }

    /**
     * Удаляет товар по его id.
     *
     * @param id id товара.
     */
    @Operation(
            summary = "Удалить товар",
            tags = {"ProductController"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Товар удален"),
                    @ApiResponse(responseCode = "404", description = "Ресурс не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
                    })
            }
    )
    @DeleteMapping("/{id}")
    public void deleteProduct(@Parameter(name = "id", description = "id товара", required = true, in = ParameterIn.PATH)
                              @PathVariable("id") UUID id) {
        productService.deleteProduct(id);
    }

}

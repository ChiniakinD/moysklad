package ru.sklad.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sklad.exception.BadRequestException;
import ru.sklad.exception.NotFoundException;
import ru.sklad.model.ApiError;

/**
 * Глобальный обработчик исключений.
 *
 * @author ChiniakinD
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        return buildExceptionResponse(
                HttpStatus.BAD_REQUEST,
                "Некорректный запрос",
                e
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFoundException(NotFoundException e) {
        return buildExceptionResponse(
                HttpStatus.NOT_FOUND,
                "Данные не найдены",
                e
        );
    }

    /**
     * Создает объект с информацией об ошибке.
     *
     * @param httpStatus статус ответа.
     * @param errorText  текст сообщения об ошибке.
     * @param throwable  исключение, содержащее информацию об ошибке.
     * @return объеки {@link ApiError}.
     */
    private ResponseEntity<ApiError> buildExceptionResponse(HttpStatus httpStatus, String errorText, Throwable throwable) {
        ApiError apiError = new ApiError()
                .setStatus(httpStatus.getReasonPhrase())
                .setDetails(throwable.getMessage())
                .setMessage(errorText);
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiError);
    }

}

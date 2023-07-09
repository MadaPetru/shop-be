package ro.adi.shop.products.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.adi.shop.products.ProductController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice(assignableTypes = {ProductController.class})
public class ProductControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(RuntimeException exception, WebRequest request) {
        var body = String.format("Application failed with exception: %s and with the message: %s", exception.toString(), exception.getMessage());
        return handleExceptionInternal(exception, body, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }
}

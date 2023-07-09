package ro.adi.shop.images.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.adi.shop.images.ImageController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice(assignableTypes = ImageController.class)
public class ImageControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(RuntimeException exception, WebRequest request) {
        var body = String.format("Application failed with exception: %s and with the message: %s", exception.toString(), exception.getMessage());
        return handleExceptionInternal(exception, body, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }
}

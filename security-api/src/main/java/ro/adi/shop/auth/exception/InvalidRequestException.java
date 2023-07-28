package ro.adi.shop.auth.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String errorMessage) {
        super(errorMessage);
    }
}

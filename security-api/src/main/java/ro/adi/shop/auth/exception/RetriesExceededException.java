package ro.adi.shop.auth.exception;

public class RetriesExceededException extends RuntimeException {

    public RetriesExceededException(String errorMessage) {
        super(errorMessage);
    }
}

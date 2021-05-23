package be.vdab.emailservice.infrastructure.exception;

public class JsonException extends RuntimeException {
    
    public static JsonException from(Exception cause) {
        return new JsonException(cause.getMessage(), cause);
    }
    
    private JsonException(String message, Exception cause) {
        super(message, cause);
    }
}

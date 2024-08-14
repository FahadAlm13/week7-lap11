package spring.boot.lap11.ApiResponse;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}

package exceptions;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {

    private final int code;

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

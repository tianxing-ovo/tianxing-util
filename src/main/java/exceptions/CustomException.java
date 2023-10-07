package exceptions;

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CustomException extends RuntimeException {

    private final int code;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(int code) {
        this.code = code;
    }
}

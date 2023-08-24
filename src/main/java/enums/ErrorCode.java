package enums;

/**
 * 错误状态码枚举
 */
public enum ErrorCode {
    UNAUTHORIZED(201, "权限不足"),
    LOGIN_FAILED(202, "登录失败"),
    TOKEN_IS_NULL(203, "token为空"),
    TOKEN_IS_MISTAKE(204, "token错误"),
    USER_HAS_EXITED(205, "用户已退出,请重新登录");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用响应对象
 */
@Data
@AllArgsConstructor
public class R<T> {
    private Integer code; //状态码
    private String msg; //消息
    private T data; //数据

    public R(String msg) {
        this.msg = msg;
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     */
    public static <T> R<T> ok() {
        return new R<>(200, "success");
    }

    /**
     * 成功
     */
    public static <T> R<T> ok(T data) {
        return new R<>(200, "success", data);
    }

    /**
     * 失败
     */
    public static <T> R<T> error(Integer code, String msg) {
        return new R<>(code, msg);
    }
}

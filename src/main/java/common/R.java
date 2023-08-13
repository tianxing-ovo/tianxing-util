package common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用响应对象
 */
@Data
@AllArgsConstructor
public class R {
    private Integer code; //状态码
    private String msg; //消息
    private Map<String, Object> dataMap = new HashMap<>(); //存放数据

    public R(String msg) {
        this.msg = msg;
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功
     */
    public static R ok() {
        return new R(200, "success");
    }

    /**
     * 成功
     */
    public static R ok(String msg) {
        return new R(200, msg);
    }

    public R put(String key, Object value) {
        dataMap.put(key, value);
        return this;
    }

    /**
     * 失败
     */
    public static R error(Integer code, String msg) {
        return new R(code, msg);
    }
}

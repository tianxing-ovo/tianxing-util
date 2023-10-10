package io.github.tianxingovo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用响应对象
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class R {
    private Integer code; //状态码
    private String msg; //消息
    private Map<String, Object> data; //存放数据

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
    public static R ok(String msg, Map<String, Object> data) {
        return new R(200, msg, data);
    }

    /**
     * 成功
     */
    public static R ok(String msg) {
        return new R(200, msg);
    }

    /**
     * 失败
     */
    public static R error(Integer code, String msg) {
        return new R(code, msg);
    }

    /**
     * 失败
     */
    public static R error(Integer code, String msg, Map<String, Object> data) {
        return new R(code, msg, data);
    }

    /**
     * 存放数据
     */
    public R put(String key, Object value) {
        data = new HashMap<>();
        data.put(key, value);
        return this;
    }
}

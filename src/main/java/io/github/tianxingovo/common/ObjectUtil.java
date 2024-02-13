package io.github.tianxingovo.common;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对象工具类
 */
public class ObjectUtil {

    /**
     * Object -> Map
     *
     * @param t 对象
     */
    @SneakyThrows
    public static <T> Map<String, Object> toMap(T t) {
        // key = 字段名, value = 字段值
        Map<String, Object> map = new LinkedHashMap<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(t));
        }
        return map;
    }

    /**
     * Object -> Map
     *
     * @param t             对象
     * @param fieldNameList 字段名称列表
     */
    @SneakyThrows
    public static <T> Map<String, Object> toMap(T t, List<String> fieldNameList) {
        Class<?> clazz = t.getClass();
        // key = 字段名, value = 字段值
        Map<String, Object> map = new LinkedHashMap<>();
        for (String fieldName : fieldNameList) {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            map.put(fieldName, field.get(t));
        }
        return map;
    }

    /**
     * Object类型转换为List类型
     */
    public static List<String> castToList(Object obj) {
        if (obj instanceof List) {
            List<?> rawList = (List<?>) obj;
            return rawList.stream().map(item -> (String) item).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Object类型转换为Map类型
     */
    public static Map<String, Object> castToMap(Object obj) {
        if (obj instanceof Map) {
            Map<?, ?> rawMap = (Map<?, ?>) obj;
            return rawMap.entrySet().stream().collect(Collectors.toMap(
                    entry -> (String) entry.getKey(),
                    Map.Entry::getValue));
        }
        return null;
    }
}

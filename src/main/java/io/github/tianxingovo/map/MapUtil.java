package io.github.tianxingovo.map;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {
    /**
     * map转object
     */
    @SneakyThrows
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        T t = clazz.newInstance();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key = Character.toUpperCase(key.charAt(0)) + key.substring(1);
                Method method;
                if (value instanceof List) {
                    method = clazz.getMethod("set" + key, List.class);
                } else {
                    method = clazz.getMethod("set" + key, value.getClass());
                }
                method.invoke(t, value);
            }
        }
        return t;
    }

    /**
     * object转map
     */
    @SneakyThrows
    public static Map<String, Object> objectToMap(Object o) {
        // 保持元素的插入顺序
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(o);
            map.put(name, value);
        }
        return map;
    }
}


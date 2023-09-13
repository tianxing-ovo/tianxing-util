package map;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
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
}


package io.github.tianxingovo.map;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Map工具类
 */
public class MapUtil {

    /**
     * map转object
     *
     * @param map   Map
     * @param clazz 目标类
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

    /**
     * 将一个Map按照固定大小拆分成多个子Map
     *
     * @param map  Map
     * @param size 子Map的大小
     * @return 子Map列表
     */
    public static <K, V> List<Map<K, V>> partition(Map<K, V> map, int size) {
        Objects.requireNonNull(map, "Map must not be null");
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        List<Map<K, V>> list = new ArrayList<>();
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map<K, V> subMap = new HashMap<>(size);
            for (int i = 0; i < size && iterator.hasNext(); i++) {
                Map.Entry<K, V> entry = iterator.next();
                subMap.put(entry.getKey(), entry.getValue());
            }
            list.add(subMap);
        }
        return list;
    }
}


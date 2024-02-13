package io.github.tianxingovo.map;

import io.github.tianxingovo.common.ObjectUtil;
import lombok.SneakyThrows;

import java.util.*;

/**
 * Map工具类
 */
public class MapUtil {

    public static final String PREFIX = "set";

    /**
     * map转object
     *
     * @param map   Map
     * @param clazz 目标类
     */
    @SneakyThrows
    public static <T> T toObject(Map<String, Object> map, Class<T> clazz) {
        T t = clazz.newInstance();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String name = PREFIX + key.substring(0, 1).toUpperCase(Locale.ROOT) + key.substring(1);
            if (Objects.isNull(value)) {
                continue;
            }
            if (value instanceof Map) {
                clazz.getMethod(name, Map.class).invoke(t, ObjectUtil.castToMap(value));
            } else if (value instanceof List) {
                clazz.getMethod(name, List.class).invoke(t, ObjectUtil.castToList(value));
            } else {
                clazz.getMethod(name, value.getClass()).invoke(t, value);
            }
        }
        return t;
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


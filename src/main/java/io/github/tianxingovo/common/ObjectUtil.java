package io.github.tianxingovo.common;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对象工具类
 */
public class ObjectUtil {
    /**
     * 获取对象属性,收集到List中
     */
    public static <T> List<Object> getProperty(Class<T> clazz, T t, List<String> fieldList) {
        List<Field> list = Arrays.asList(clazz.getDeclaredFields());
        // 获取指定属性
        if (fieldList != null) {
            list = list.stream().filter(field -> fieldList.contains(field.getName())).collect(Collectors.toList());
        }
        return list.stream().map(field -> {
            field.setAccessible(true);
            try {
                return field.get(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * 获取对象的所有属性,收集到List中
     */
    public static <T> List<Object> getProperty(Class<T> clazz, T t) {
        return getProperty(clazz, t, null);
    }
}

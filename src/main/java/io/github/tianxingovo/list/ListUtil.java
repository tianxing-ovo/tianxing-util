package io.github.tianxingovo.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * List工具类
 */
public class ListUtil {

    /**
     * 将一个List按照固定大小拆分成多个子List
     *
     * @param list 列表
     * @param size 子List的大小
     */
    public static <T> List<List<T>> partition(List<T> list, int size) {
        Objects.requireNonNull(list, "List must not be null");
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        List<List<T>> lists = new ArrayList<>();
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            List<T> subList = new ArrayList<>(size);
            for (int i = 0; i < size && iterator.hasNext(); i++) {
                subList.add(iterator.next());
            }
            lists.add(subList);
        }
        return lists;
    }
}

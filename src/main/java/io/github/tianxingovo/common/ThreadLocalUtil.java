package io.github.tianxingovo.common;

/**
 * 多线程环境中保存和获取用户相关的信息
 */
@SuppressWarnings("unchecked")
public class ThreadLocalUtil {

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static <T> void set(T t) {
        threadLocal.set(t);
    }

    public static <T> T get() {
        return (T) threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}


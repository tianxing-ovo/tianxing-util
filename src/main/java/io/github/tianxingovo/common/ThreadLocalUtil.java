package io.github.tianxingovo.common;

/**
 * 多线程环境中保存和获取用户相关的信息
 */
public class ThreadLocalUtil<T> {

    private final ThreadLocal<T> threadLocal = new ThreadLocal<>();

    public void set(T t) {
        threadLocal.set(t);
    }

    public T get() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }
}


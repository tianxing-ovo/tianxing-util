package io.github.tianxingovo.common;

import java.util.Random;

/**
 * 数字工具类
 */
public class NumUtil {

    /**
     * 获取n位随机整数
     */
    public static int randomNum(int n) {
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n) - 1;
        return new Random().nextInt(max - min + 1) + min;
    }
}

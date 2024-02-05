package io.github.tianxingovo.csv;

import io.github.tianxingovo.common.ObjectUtil;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * csv工具类
 */
public class CsvUtil {

    public static final StringBuilder sb = new StringBuilder();

    public static void write(List<?> list, List<String> filedList) {
        for (Object obj : list) {
            sb.append(ObjectUtil.getProperty(obj, filedList).stream().map(CsvUtil::format).collect(Collectors.joining(","))).append("\n");
        }
    }

    /**
     * @param filedList 字段名称列表
     */
    public static void writeHead(List<String> filedList) {
        sb.append(String.join(",", filedList)).append("\n");
    }

    /**
     * 导出csv文件
     *
     * @param path     基础路径: C:/Users/admin/Desktop
     * @param fileName 文件名: test.csv
     */
    @SneakyThrows
    public static void export(String path, String fileName) {
        Files.write(Paths.get(path, fileName), sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 处理特殊情况
     */
    private static String format(String str) {
        if (str == null || str.equals("null")) {
            return "--";
        }
        return str;
    }
}

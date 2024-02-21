package io.github.tianxingovo.csv;

import io.github.tianxingovo.constant.Constant;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * csv工具类
 */
public class CsvUtil {

    public static final StringBuilder sb = new StringBuilder();


    /**
     * 部分字段写入表体
     *
     * @param list          数据List
     * @param fieldNameList 字段名称列表
     */
    public static void write(List<Map<String, Object>> list, List<String> fieldNameList) {
        for (Map<String, Object> map : list) {
            sb.append(fieldNameList.stream().map(fieldName -> format(map.get(fieldName))).collect(Collectors.joining(Constant.COMMA))).append(Constant.LINE_BREAK);
        }
    }


    /**
     * 全部字段写入表体
     *
     * @param list 数据List
     */
    public static void write(List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            sb.append(map.values().stream().map(CsvUtil::format).collect(Collectors.joining(Constant.COMMA)))
                    .append(Constant.LINE_BREAK);
        }
    }

    /**
     * 写入表头
     *
     * @param filedList 字段名称列表
     */
    public static void writeHead(List<String> filedList) {
        sb.append(String.join(Constant.COMMA, filedList)).append(Constant.LINE_BREAK);
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
    private static String format(Object obj) {
        if (Objects.isNull(obj)) {
            return Constant.DOUBLE_DASH;
        }
        return String.valueOf(obj);
    }
}

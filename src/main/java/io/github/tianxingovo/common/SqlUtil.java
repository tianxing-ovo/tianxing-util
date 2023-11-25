package io.github.tianxingovo.common;

import java.util.Map;
import java.util.StringJoiner;

/**
 * 数据库工具类,用于生成SQL语句
 */
public class SqlUtil {

    /**
     * 生成批量更新语句
     *
     * @param updateField 需要更新的字段
     * @param field       case的字段
     */
    public static String batchUpdateSql(String tableName, String updateField, String field, Map<Integer, String> map) {
        String prefix = String.format("update %s set %s = case %s ", tableName, updateField, field);
        StringJoiner joiner = new StringJoiner(" ", prefix, " end");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String s = String.format("when %d then %s", entry.getKey(), entry.getValue());
            joiner.add(s);
        }
        return joiner.toString();
    }
}

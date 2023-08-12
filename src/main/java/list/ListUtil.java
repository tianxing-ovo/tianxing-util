package list;

import java.util.List;
import java.util.stream.Collectors;

/**
 * List工具类
 */
public class ListUtil {

    /**
     * Object -> List
     */
    public static List<String> objectToList(Object o) {
        if (o instanceof List) {
            List<?> rawList = (List<?>) o;
            List<String> list;
            list = rawList.stream().map(item -> (String) item).collect(Collectors.toList());
            return list;
        }
        return null;
    }
}

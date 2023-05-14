package utilclasses;
import playerclasses.playerStats;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {
    public static <String, playerStats extends Comparable<? super playerStats>> Map<String, playerStats> sortMatchHistoryByValue(Map<String, playerStats> map) {
        List<Map.Entry<String, playerStats>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<String, playerStats> result = new LinkedHashMap<>();
        for (Map.Entry<String, playerStats> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}

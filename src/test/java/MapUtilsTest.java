import cn.edu.dll.io.print.MyPrint;
import cn.edu.dll.map.MapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapUtilsTest {
    @Test
    public void fun1() {
        Map<Integer, Map<Integer, Integer>> dataMap = new HashMap<>();
        Integer upperBound = 10;
        for (int i = 0; i < upperBound; i++) {
            for (int j = i + 1; j < upperBound; j++) {
                MapUtils.addTwoIndexValue(dataMap, i, j, 100*i+10*j+2);
            }
        }
        String result = MapUtils.getTwoIndexMapString(dataMap);
        System.out.println(result);
        MyPrint.showSplitLine("*", 150);

        MapUtils.shrink(dataMap, 2);

        System.out.println(MapUtils.getTwoIndexMapString(dataMap));
    }
}

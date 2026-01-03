import cn.edu.dll.basic.BasicArrayUtil;
import cn.edu.dll.differential_privacy.ldp.consistent.Normalization;
import cn.edu.dll.io.print.MyPrint;
import org.junit.Test;

import java.util.*;

public class BasicTest {
    @Test
    public void fun1() {
//        Double[] initializedArray = BasicArrayUtil.getInitializedArray(2.2, 10);
//        MyPrint.showArray(initializedArray);
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        List<Map<String,String>> stringList = BasicArrayUtil.getInitializedList(map, 3);
        MyPrint.showList(stringList);
    }

    @Test
    public void fun2() {
        List<Double> userEpsilonList = Arrays.asList(0.1, 0.4, 0.4, 0.1, 0.4, 0.4, 0.8, 0.8, 0.8, 0.4);
        List<Double> uniqueList = BasicArrayUtil.getUniqueList(userEpsilonList);
        MyPrint.showList(uniqueList);
    }

    @Test
    public void fun3() {
        List<Double> userEpsilonList = Arrays.asList(0.1, 0.4, 0.4, 0.1, 0.4, 0.4, 0.8, 0.8, 0.8, 0.4);
        LinkedHashMap<Double, Integer> resultMap = BasicArrayUtil.getUniqueListWithCountList(userEpsilonList);
        MyPrint.showMap(resultMap);
    }

    @Test
    public void fun4() {
        List<Double> data = Arrays.asList(-0.02, 0.35, 0.15, 0.05, -0.01);
        List<Double> result = Normalization.normalizedByTruncation(data);
        MyPrint.showList(result);
    }

    @Test
    public void fun5() {
        List<Double> estimate = Arrays.asList(-0.02, 0.35, 0.15, 0.05, -0.01);

        System.out.println("原始估计: " + estimate);

        List<Double> projected = Normalization.normalizedBySimplexProjection(estimate);

        System.out.println("投影后: " + projected);
        System.out.println("和: " + projected.stream().mapToDouble(Double::doubleValue).sum());
    }
}

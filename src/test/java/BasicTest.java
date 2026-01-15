import cn.edu.dll.basic.BasicArrayUtil;
import cn.edu.dll.basic.ValidationUtil;
import cn.edu.dll.math.differential_privacy.ldp.consistent.Normalization;
import cn.edu.dll.io.print.MyPrint;
import org.junit.Test;

import java.util.*;

public class BasicTest {
    @Test
    public void setEqualTest() {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new TreeSet<>(Arrays.asList(3, 2, 1));
        Set<Integer> set3 = new LinkedHashSet<>(Arrays.asList(2, 1, 3));

        System.out.println(Objects.equals(set1, set2)); // true
        System.out.println(Objects.equals(set1, set3)); // true
        System.out.println(Objects.equals(set2, set3)); // true
    }

    @Test
    public void requireNoMoreThanTest() {
        Integer dataA = 46;
        Integer dataB = 46;
        boolean result = ValidationUtil.requireNoMoreThan(dataA, dataB, "dataA needs to be no more than dataB!");
        System.out.println(result);
    }


}

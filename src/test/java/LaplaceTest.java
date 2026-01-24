import cn.edu.dll.math.differential_privacy.noise.LaplaceNoise;
import org.junit.Test;

import java.util.Random;

public class LaplaceTest {
    @Test
    public void fun1() {
        Random random = new Random(1);
        LaplaceNoise laplaceNoise = new LaplaceNoise(2, 0.5, random);
        double result = laplaceNoise.getLaplaceNoise();
        System.out.println(result);
    }
}

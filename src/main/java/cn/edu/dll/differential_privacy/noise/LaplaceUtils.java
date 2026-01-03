package cn.edu.dll.differential_privacy.noise;

import cn.edu.dll.basic.random_adapter.RandomGeneratorAdapter;
import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.apache.commons.math3.random.RandomAdaptor;
import org.apache.commons.math3.random.RandomGenerator;

import java.util.Random;

public class LaplaceUtils {
    private LaplaceDistribution laplaceDistribution = null;

    public LaplaceUtils(double sensitivity, double epsilon) {
        this.laplaceDistribution = new LaplaceDistribution(0, sensitivity / epsilon);
    }

    public LaplaceUtils(double sensitivity, double epsilon, RandomGenerator randomGenerator) {
        this.laplaceDistribution = new LaplaceDistribution(randomGenerator, 0, sensitivity / epsilon);
    }

    public LaplaceUtils(double sensitivity, double epsilon, Random random) {
        RandomGenerator randomGenerator = new RandomGeneratorAdapter(random);
        this.laplaceDistribution = new LaplaceDistribution(randomGenerator, 0, sensitivity / epsilon);
    }

    public static double[] getLaplaceNoise(double sensitivity, double epsilon, int number){
        LaplaceDistribution laplaceDistribution = new LaplaceDistribution(0, sensitivity/epsilon);
        double[] result = laplaceDistribution.sample(number);
        return result;
    }

    public static double getLaplaceNoise(double sensitivity, double epsilon) {
        LaplaceDistribution laplaceDistribution = new LaplaceDistribution(0, sensitivity / epsilon);
        return laplaceDistribution.sample();
    }

    public double[] getLaplaceNoise(int number) {
        return this.laplaceDistribution.sample(number);
    }

    public double getLaplaceNoise() {
        return this.laplaceDistribution.sample();
    }

}

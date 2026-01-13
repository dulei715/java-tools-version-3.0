package cn.edu.dll.math.differential_privacy.noise;

import cn.edu.dll.math.random_adapter.RandomGeneratorAdapter;
import org.apache.commons.math3.distribution.LaplaceDistribution;
import org.apache.commons.math3.random.RandomGenerator;

import java.util.Random;

public class LaplaceNoise {
    protected LaplaceDistribution laplaceDistribution = null;

    public LaplaceNoise(double sensitivity, double epsilon) {
        this.laplaceDistribution = new LaplaceDistribution(0, sensitivity / epsilon);
    }

    public LaplaceNoise(double sensitivity, double epsilon, RandomGenerator randomGenerator) {
        this.laplaceDistribution = new LaplaceDistribution(randomGenerator, 0, sensitivity / epsilon);
    }

    public LaplaceNoise(double sensitivity, double epsilon, Random random) {
        RandomGenerator randomGenerator = new RandomGeneratorAdapter(random);
        this.laplaceDistribution = new LaplaceDistribution(randomGenerator, 0, sensitivity / epsilon);
    }



    public double[] getLaplaceNoise(int number) {
        return this.laplaceDistribution.sample(number);
    }

    public double getLaplaceNoise() {
        return this.laplaceDistribution.sample();
    }

}

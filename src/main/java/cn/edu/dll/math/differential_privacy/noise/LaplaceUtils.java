package cn.edu.dll.math.differential_privacy.noise;

import org.apache.commons.math3.distribution.LaplaceDistribution;

public class LaplaceUtils {
        public static double[] getLaplaceNoise(double sensitivity, double epsilon, int number){
        LaplaceDistribution laplaceDistribution = new LaplaceDistribution(0, sensitivity/epsilon);
        double[] result = laplaceDistribution.sample(number);
        return result;
    }

    public static double getLaplaceNoise(double sensitivity, double epsilon) {
        LaplaceDistribution laplaceDistribution = new LaplaceDistribution(0, sensitivity / epsilon);
        return laplaceDistribution.sample();
    }
}

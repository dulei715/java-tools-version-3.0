package cn.edu.dll.differential_privacy.noise;

import cn.edu.dll.basic.NumberUtil;

import java.util.Random;

public class GaussUtils {
    private Random random = new Random();

//    public GaussUtils(double sensitivity, double epsilon, double delta) {
//        this.gaussDistribution = new Gaussian(0, Math.sqrt(2*Math.log(1/delta))*sensitivity/epsilon);
//    }
    public static double[] getGaussNoise(double sensitivity, double epsilon, double delta, int number){
        Random tempRandom = new Random();
        double standardVariance = Math.sqrt(2*Math.log(1/delta))*sensitivity/epsilon;
        double[] result = new double[number];
        for (int i = 0; i < number; i++) {
            result[i] = tempRandom.nextGaussian()*standardVariance;
        }
        return result;
    }

    public double getGaussNoise(double sensitivity, double epsilon, double delta) {
        double standardVariance = Math.sqrt(2*Math.log(1/delta))*sensitivity/epsilon;
        return this.random.nextGaussian()*standardVariance;
    }

    public double[] getGaussNoise(int number) {
        double[] result = new double[number];
        for (int i = 0; i < number; i++) {
            result[i] = this.random.nextGaussian();
        }
        return result;
    }

    public double getGaussNoise(double average, double standardVariance) {
        return this.random.nextGaussian() * standardVariance + average;
    }

    public double[] getGaussNoise(double average, double standardVariance, int size) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = getGaussNoise(average, standardVariance);
        }
        return result;
    }

    public double[] getGaussNoise(double average, double standardVariance, int size, int precision) {
        double[] result = new double[size];
        double tempDouble;
        for (int i = 0; i < size; i++) {
            tempDouble = getGaussNoise(average, standardVariance);
            result[i] = NumberUtil.roundFormat(tempDouble, precision);

//            String tempString = String.valueOf(result[i]);
//            if (tempString.substring(tempString.indexOf(".")).length()>3) {
//                System.out.println("woc");
//            }
        }
        return result;
    }

//    public double getLaplaceNoise() {
//        return this.random.nextGaussian();
//    }

}

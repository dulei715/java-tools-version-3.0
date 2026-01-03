package cn.edu.dll.differential_privacy.ldp.frequency_oracle.foImp;

import cn.edu.dll.basic.BasicArrayUtil;
import cn.edu.dll.basic.BasicCalculation;
import cn.edu.dll.differential_privacy.ldp.frequency_oracle.FrequencyOracle;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GeneralizedRandomizedResponse implements FrequencyOracle<Integer, Integer> {

    private double epsilon;
    private double p;
    private double q;
    private int size;

    private Random random;

    public GeneralizedRandomizedResponse(Double epsilon, Integer size) {
        this.epsilon = epsilon;
        this.size = size;
        this.p = Math.exp(epsilon) / (Math.exp(epsilon) + this.size - 1);
        this.q = 1 / (Math.exp(epsilon) + this.size - 1);

        random = new Random();
//        Arrays.quickSort(this.data);
    }

    /**
     * Get the random value of the array excluding the value on index position
     * @param index
     * @return
     */
    private Integer getRandomDataExcept(int index) {
        int pos = this.random.nextInt(this.size - 1);
        if (pos < index) {
            return pos;
        }
        return pos + 1;
    }




    @Override
    public Integer perturb(Integer rawData) {
        double randomPoint = Math.random();
        if (randomPoint < this.p) {
            return rawData;
        }
        return getRandomDataExcept(rawData);
    }

    @Override
    public Integer rePerturb(Integer rawData, Double probabilityAlpha, Double probabilityBeta) {
        double randomPoint = Math.random();
        if (randomPoint < probabilityAlpha) {
            return rawData;
        }
        return getRandomDataExcept(rawData);
    }

    @Override
    public Map<Integer, Integer> aggregate(List<Integer> perturbedData) {
        return BasicArrayUtil.getUniqueListWithCountList(perturbedData);
    }

    @Override
    public double estimate(int noiseEstimateCount, int userSize) {
        return (noiseEstimateCount * 1.0 / userSize - this.q)/(this.p - this.q);
    }

    @Override
    public double getVariance(int userSize, double realValueFrequency) {
        return userSize * q *(1 - q) / Math.pow(p - q, 2) + userSize * realValueFrequency * (1 - p - q) / (p - q);
    }

    @Override
    public double getApproximateVariance(int userSize) {
        return userSize * q * (1 - q) / Math.pow(p - q, 2);
    }

    @Override
    public double getP() {
        return this.p;
    }

    @Override
    public double getQ() {
        return this.q;
    }

    public static void main(String[] args) {

    }


}

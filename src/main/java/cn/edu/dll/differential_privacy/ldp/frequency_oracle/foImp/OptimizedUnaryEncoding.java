package cn.edu.dll.differential_privacy.ldp.frequency_oracle.foImp;

import cn.edu.dll.basic.BasicArrayUtil;
import cn.edu.dll.basic.RandomUtil;
import cn.edu.dll.constant_values.ConstantValues;
import cn.edu.dll.differential_privacy.ldp.frequency_oracle.FrequencyOracle;
import cn.edu.dll.io.print.MyPrint;
import cn.edu.dll.struct.one_hot.OneHot;
import cn.edu.dll.struct.one_hot.SimpleIntegerOneHot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class OptimizedUnaryEncoding implements FrequencyOracle<Integer, OneHot<Integer>> {

    private double epsilon;
    private int size;
    private double q;
//    private Random random;
//    private OneHot<T> oneHot;

    public OptimizedUnaryEncoding(Double epsilon, Integer size) {
        this.size = size;
        this.epsilon = epsilon;
        this.q = 1.0 / (Math.exp(epsilon)+1);
//        this.random = new Random();
//        this.oneHot = oneHot;
    }

    public void resetEpsilon(double epsilon) {
        this.q = 1.0 / (Math.exp(epsilon)+1);
    }

    @Override
    public OneHot<Integer> perturb(Integer rawData) {
        SimpleIntegerOneHot oneHotOriginalData = new SimpleIntegerOneHot(this.size);
        oneHotOriginalData.setPosition(rawData);
        boolean[] data = oneHotOriginalData.getData();
        boolean[] resultData = new boolean[data.length];
        double probability;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == OneHot.ONE) {
                probability = 0.5;
            } else {
                probability = q;
            }
            resultData[i] = RandomUtil.isChosen(probability) ? OneHot.ONE : OneHot.ZERO;
        }
        return oneHotOriginalData.getInstance(resultData);
    }

    @Override
    public OneHot<Integer> rePerturb(Integer rawData, Double probabilityAlpha, Double probabilityBeta) {
        SimpleIntegerOneHot oneHotOriginalData = new SimpleIntegerOneHot(this.size);
        oneHotOriginalData.setPosition(rawData);
        boolean[] data = oneHotOriginalData.getData();
        boolean[] resultData = new boolean[data.length];
        double probability;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == OneHot.ONE) {
                probability = probabilityAlpha;
            } else {
                probability = probabilityBeta;
            }
            resultData[i] = RandomUtil.isChosen(probability) ? OneHot.ONE : OneHot.ZERO;
        }
        return oneHotOriginalData.getInstance(resultData);
    }

    @Override
    public Map<OneHot<Integer>, Integer> aggregate(List<OneHot<Integer>> perturbedData) {
        return BasicArrayUtil.getUniqueListWithCountList(perturbedData);
    }

    @Override
    public double estimate(int targetNoiseEstimateCount, int userSize) {
        return (targetNoiseEstimateCount - userSize * this.q) / (0.5 - this.q);
    }

    @Override
    public double getVariance(int userSize, double realValueFrequency) {
        return userSize * q *(1 - q) / Math.pow(0.5 - q, 2) + userSize * realValueFrequency;
    }

    @Override
    public double getApproximateVariance(int userSize) {
        return userSize * q *(1 - q) / Math.pow(0.5 - q, 2);
    }



    public static <T> Integer[] count(Collection<OneHot<T>> dataCollection) {
        int colSize = dataCollection.iterator().next().getAreaSize();
        Integer[] result = new Integer[colSize];
        boolean[] tempOneHot;
        BasicArrayUtil.setIntArrayToZero(result);
        for (OneHot<T> oneHot : dataCollection) {
            tempOneHot = oneHot.getData();
            for (int i = 0; i < colSize; i++) {
                result[i] += tempOneHot[i] ? 1 : 0;
            }
        }
        return result;
    }
    public static <T> Integer[] countMultiple(Collection<List<OneHot<T>>> dataListCollection) {
        int colSize = dataListCollection.iterator().next().get(0).getAreaSize();
        Integer[] result = new Integer[colSize];
        boolean[] tempOneHot;
        BasicArrayUtil.setIntArrayToZero(result);
        for (List<OneHot<T>> dataList : dataListCollection) {
            for (OneHot<T> oneHot : dataList) {
                tempOneHot = oneHot.getData();
                for (int i = 0; i < colSize; i++) {
                    result[i] += tempOneHot[i] ? 1 : 0;
                }
            }
        }
        return result;
    }

    public Double[] unbias(Integer[] perturbedCount, int userSize) {
        Double[] result = new Double[perturbedCount.length];
        for (int i = 0; i < perturbedCount.length; i++) {
            result[i] = this.estimate(perturbedCount[i], userSize);
        }
        return result;
    }

    @Override
    public double getQ() {
        return q;
    }

    @Override
    public double getP() {
        return 0.5;
    }

}

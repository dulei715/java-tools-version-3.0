package cn.edu.dll.math.differential_privacy.cdp.mechanism;

import cn.edu.dll.basic.BasicArrayUtil;
import cn.edu.dll.basic.BasicSearch;
import cn.edu.dll.math.BasicCalculation;
import cn.edu.dll.math.differential_privacy.noise.LaplaceNoise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoisyMaxMechanism {
    protected Random random;
    protected LaplaceNoise laplaceNoise;
    protected Double epsilon;
    protected Double deltaQ;

    public NoisyMaxMechanism(Double epsilon, Double deltaQ, Random random) {
        this.random = random;
        this.epsilon = epsilon;
        this.deltaQ = deltaQ;
        this.laplaceNoise = new LaplaceNoise(2 * deltaQ, epsilon);
    }

    public NoisyMaxMechanism(Double epsilon, Double deltaQ) {
        this.random = new Random();
        this.epsilon = epsilon;
        this.deltaQ = deltaQ;
    }

    public Integer getNoiseMaxValueIndex(List<Double> realValueList) {
        int valueSize = realValueList.size();
        List<Double> noiseValueList = new ArrayList<>(valueSize);
        Double noiseValue;
        for (Double realValue : realValueList) {
            noiseValue = realValue + this.laplaceNoise.getLaplaceNoise();
            noiseValueList.add(noiseValue);
        }
        return BasicArrayUtil.getDoubleMaxPair(noiseValueList).getKey();
    }

}

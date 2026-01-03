package cn.edu.dll.differential_privacy.ldp.frequency_oracle;

import java.util.List;
import java.util.Map;

public interface FrequencyOracle<I,O> {
    /**
     * Used by each user to perturb her input value
     * @param rawData
     * @return
     */
    O perturb(I rawData);

    O rePerturb(I rawData, Double probabilityAlpha, Double probabilityBeta);

    Map<O, Integer> aggregate(List<O> perturbedData);

    /**
     * Used by the aggregator to aggregate and unbia values
     * @return
     */
    double estimate(int targetNoiseEstimateCount, int userSize);

    double getVariance(int userSize, double realValueFrequency);

    double getApproximateVariance(int userSize);

    double getP();
    double getQ();
}

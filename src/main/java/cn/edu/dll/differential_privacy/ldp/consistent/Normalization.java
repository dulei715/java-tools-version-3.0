package cn.edu.dll.differential_privacy.ldp.consistent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Normalization {
    public static List<Double> normalizedByTruncation(List<Double> originalDataList) {
        int size = originalDataList.size();
        List<Double> resultList = new ArrayList<>(size);
        Double sum = 0D, element;
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            element = originalDataList.get(i);
            if (element < 0) {
                resultList.add(0D);
            } else {
                resultList.add(element);
                sum += element;
                indexList.add(i);
            }
        }
        for (Integer index : indexList) {
            resultList.set(index, resultList.get(index) / sum);
        }
        return resultList;
    }

    public static List<Double> normalizedBySimplexProjection(List<Double> v) {
        int n = v.size();
        if (n  == 0) {
            return new ArrayList<>();
        }
        // 1. 复制到数组以便排序
        double[] u = new double[n];
        for (int i = 0; i < n; ++i) {
            u[i] = v.get(i);
        }
        // 2. 降序排序
        Arrays.sort(u);
        // reverse in-place to get descending order
        for (int i = 0; i < n / 2; ++i) {
            double temp = u[i];
            u[i] = u[n - 1 - i];
            u[n - 1 - i] = temp;
        }
        // 3. 找到最大 rho 满足: u[rho-1] > (sum_{i=0}^{rho-1} u[i] - 1) / rho
        double sumU = 0.0;
        int rho = 0;
        for (int i = 0; i < n; i++) {
            sumU += u[i];
            double threshold = u[i] - (sumU - 1.0) / (i + 1);
            if (threshold > 0) {
                rho = i + 1;
            } else {
                break;
            }
        }
        // 4. 计算 theta = (sum_{i=0}^{rho-1} u[i] - 1) / rho
        double sumUpToRho = 0.0;
        for (int i = 0; i < rho; i++) {
            sumUpToRho += u[i];
        }
        double theta = (sumUpToRho - 1.0) / rho;

        // 5. 计算结果: p_i = max(v_i - theta, 0)
        List<Double> result = new ArrayList<>(n);
        double sum = 0.0;
        for (double value : v) {
            double projectedValue = Math.max(value - theta, 0.0);
            result.add(projectedValue);
            sum += projectedValue;
        }
        // 6. 数值稳定性：确保和为1（处理浮点误差）
        if (Math.abs(sum - 1.0) > 1e-10 && sum > 0) {
            for (int i = 0; i < result.size(); i++) {
                result.set(i, result.get(i) / sum);
            }
        }
        return result;
    }

}

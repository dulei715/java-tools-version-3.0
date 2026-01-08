package cn.edu.dll.math.differential_privacy.cdp.mechanism;

import java.util.Random;

public class ExponentialMechanism {
    protected Random random;

    public ExponentialMechanism() {
        this.random = new Random();
    }

    public ExponentialMechanism(Random random) {
        this.random = random;
    }

    /**
     * 使用指数机制（通过 Gumbel 噪声）私有地选择最优索引
     *
     * @param scores   每个候选选项的效用分数（长度 >= 1）
     * @param epsilon  隐私预算 ε > 0
     * @param deltaU   效用函数的全局敏感度 Δu > 0
     * @return 被选中的索引（0 到 scores.length - 1）
     */

    public int select(double[] scores, double epsilon, double deltaU) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException("Scores array must not be empty");
        }
        if (epsilon <= 0 || deltaU <= 0) {
            throw new IllegalArgumentException("epsilon and deltaU must be positive");
        }


        int n = scores.length;
        double[] noisyScores = new double[n];


        // Gumbel 噪声尺度: b = 2 * deltaU / epsilon
        double scale = 2.0 * deltaU / epsilon;

        for (int i = 0; i < n; i++) {
            double gumbelNoise = sampleGumbel(scale);
            noisyScores[i] = scores[i] + gumbelNoise;
        }


        // 返回 argmax

        int bestIndex = 0;
        for (int i = 1; i < n; i++) {
            if (noisyScores[i] > noisyScores[bestIndex]) {
                bestIndex = i;
            }
        }

        return bestIndex;

    }


    /**
     * 从 Gumbel(0, scale) 分布中采样
     * Gumbel(μ, β) 的标准形式：μ - β * ln(-ln(U)), U ~ Uniform(0,1)
     * 这里 μ = 0, β = scale
     */

    protected double sampleGumbel(double scale) {

        double u;

        do {
            u = random.nextDouble(); // (0.0, 1.0)
        } while (u == 0.0 || u == 1.0); // 避免 log(0)
        return -scale * Math.log(-Math.log(u));

    }


    // ================== 示例用法 ==================

    public static void main(String[] args) {
        // 示例：3 个关键词的出现次数（效用 = 计数）
        double[] counts = {100, 98, 95}; // "AI", "R", "Python"
        double epsilon = 1.0;
        double deltaU = 1.0; // 计数查询的敏感度为 1

        ExponentialMechanism exponentialMechanism = new ExponentialMechanism();
        // 多次运行观察随机性
        System.out.println("Running Exponential Mechanism 10 times:");
        for (int i = 0; i < 10; i++) {
            int selected = exponentialMechanism.select(counts, epsilon, deltaU);
            System.out.println("Selected index: " + selected + " (score=" + counts[selected] + ")");
        }
    }
}

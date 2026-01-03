package cn.edu.dll.basic.random_adapter;

import org.apache.commons.math3.random.RandomGenerator;

import java.util.Random;

public class RandomGeneratorAdapter implements RandomGenerator {
    private final Random random;

    public RandomGeneratorAdapter(Random random) {
        this.random = random;
    }


    @Override
    public void setSeed(int i) {
        random.setSeed(i);
    }

    @Override
    public void setSeed(int[] ints) {
        // 将整数数组转换为一个 long 类型的种子
        long combinedSeed = 0;
        for (int i = 0; i < ints.length; i++) {
            combinedSeed = (combinedSeed << 32) | (ints[i] & 0xFFFFFFFFL); // 使用位移合并整数
        }
        random.setSeed(combinedSeed);
    }

    @Override
    public void setSeed(long l) {
        random.setSeed(l);
    }

    @Override
    public void nextBytes(byte[] bytes) {
        random.nextBytes(bytes);
    }

    @Override
    public int nextInt() {
        return random.nextInt();
    }

    @Override
    public int nextInt(int i) {
        return random.nextInt(i);
    }

    @Override
    public long nextLong() {
        return random.nextLong();
    }

    @Override
    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    @Override
    public float nextFloat() {
        return random.nextFloat();
    }

    @Override
    public double nextDouble() {
        return random.nextDouble();
    }

    @Override
    public double nextGaussian() {
        return random.nextGaussian();
    }
}

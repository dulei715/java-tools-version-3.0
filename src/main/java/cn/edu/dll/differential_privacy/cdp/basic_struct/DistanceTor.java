package cn.edu.dll.differential_privacy.cdp.basic_struct;

public interface DistanceTor<T> {
    double getDistance(T elemA, T elemB);
}

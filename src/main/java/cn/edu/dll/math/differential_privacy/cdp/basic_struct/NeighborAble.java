package cn.edu.dll.math.differential_privacy.cdp.basic_struct;

public interface NeighborAble<T> {
    boolean isNeighborhoodWith(T element);
}

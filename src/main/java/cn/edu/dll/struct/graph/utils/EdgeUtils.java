package cn.edu.dll.struct.graph.utils;

import cn.edu.dll.struct.graph.Edge;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BinaryOperator;

public class EdgeUtils {
    public static <V extends Number & Comparable<V>, E extends Edge<V>> V getEdgeValueSum(Set<E> edgeSet, BinaryOperator<V> valueAdder) {
        return edgeSet.stream().map(Edge::getValue).reduce(valueAdder).orElseThrow(() -> new NoSuchElementException("No edges"));
    }
}

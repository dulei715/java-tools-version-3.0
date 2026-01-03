package cn.edu.dll.struct.graph;

import java.util.Objects;

public abstract class Edge implements Comparable<Edge> {
    protected Double value = null;

    public Edge() {
    }

    public Edge(Double value) {
        this.value = value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public boolean valueEqual(Edge edge) {
        return this.value.equals(edge.value);
    }

    /*
    要求继承该类必须重写 hashCode 和 equals方法
    规定，只有关联相同的节点才算是相等
     */
    public abstract int hashCode();
    public abstract boolean equals(Object o);
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Edge edge = (Edge) o;
//        return Objects.equals(value, edge.value);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(value);
//    }

    @Override
    public int compareTo(Edge edgeB) {
        return this.value.compareTo(edgeB.value);
    }

}

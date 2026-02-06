package cn.edu.dll.struct.graph;


public abstract class Edge<T extends Number & Comparable<T>> implements Comparable<Edge<T>> {
    protected T value = null;

    public Edge() {
    }

    public Edge(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean valueEqual(Edge<T> edge) {
        return this.value.equals(edge.value);
    }

    /*
    要求继承该类必须重写 hashCode 和 equals方法
    规定，只有关联相同的节点才算是相等
     */
    public abstract int hashCode();
    public abstract boolean equals(Object o);

    @Override
    public int compareTo(Edge<T> edgeB) {
        return this.value.compareTo(edgeB.value);
    }

}

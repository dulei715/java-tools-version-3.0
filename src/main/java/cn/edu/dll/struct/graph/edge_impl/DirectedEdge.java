package cn.edu.dll.struct.graph.edge_impl;

import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Node;

import java.util.Objects;

//@Deprecated
// 这里暂时用不到，所以先设置它为过期状态
public class DirectedEdge<V extends Number & Comparable<V>, N extends Node> extends Edge<V> {

    protected N startNode = null;
    protected N endNode = null;

    public DirectedEdge(V value, N startNode, N endNode) {
        this.value = value;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public DirectedEdge(V value) {
        this.value = value;
    }



    public N getStartNode() {
        return startNode;
    }

    public N getEndNode() {
        return endNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedEdge<?, ?> that = (DirectedEdge<?, ?>) o;
        return Objects.equals(startNode, that.startNode) && Objects.equals(endNode, that.endNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startNode, endNode);
    }

}

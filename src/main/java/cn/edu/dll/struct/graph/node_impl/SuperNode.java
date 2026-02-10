package cn.edu.dll.struct.graph.node_impl;

import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Node;

public class SuperNode<V extends Number & Comparable<V>, E extends Edge<V>> extends Node {
    protected V innerWeight;
    public SuperNode(Integer nodeID) {
        super(nodeID);
    }
}

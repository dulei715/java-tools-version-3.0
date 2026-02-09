package cn.edu.dll.struct.graph.edge_impl;

import cn.edu.dll.struct.graph.Node;

public class WeightedUndirectedEdge extends UndirectedEdge<Double>{
    public WeightedUndirectedEdge(Double value) {
        super(value);
    }

    public WeightedUndirectedEdge(Double value, Node nodeA, Node nodeB) {
        super(value, nodeA, nodeB);
    }
}

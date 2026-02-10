package cn.edu.dll.struct.graph.edge_impl.impls;

import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;

public class WeightedUndirectedEdge<N extends Node> extends UndirectedEdge<Double, N> {
    public WeightedUndirectedEdge(Double value) {
        super(value);
    }

    public WeightedUndirectedEdge(Double value, N nodeA, N nodeB) {
        super(value, nodeA, nodeB);
    }
}

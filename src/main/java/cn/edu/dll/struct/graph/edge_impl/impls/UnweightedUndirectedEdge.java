package cn.edu.dll.struct.graph.edge_impl.impls;

import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;

public class UnweightedUndirectedEdge<N extends Node> extends UndirectedEdge<Integer, N> {
    public UnweightedUndirectedEdge() {
        super(1);
    }

    public UnweightedUndirectedEdge(N nodeA, N nodeB) {
        super(1, nodeA, nodeB);
    }
}

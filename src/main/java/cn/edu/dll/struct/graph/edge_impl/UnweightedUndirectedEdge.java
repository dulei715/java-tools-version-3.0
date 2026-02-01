package cn.edu.dll.struct.graph.edge_impl;

import cn.edu.dll.struct.graph.Node;

public class UnweightedUndirectedEdge extends UndirectedEdge{
    public UnweightedUndirectedEdge() {
        super(1D);
    }

    public UnweightedUndirectedEdge(Node nodeA, Node nodeB) {
        super(1D, nodeA, nodeB);
    }
}

package cn.edu.dll.struct.graph.edge_impl;

import cn.edu.dll.struct.graph.Node;

public class UnweightedUndirectedEdge extends UndirectedEdge<Integer>{
    public UnweightedUndirectedEdge() {
        super(1);
    }

    public UnweightedUndirectedEdge(Node nodeA, Node nodeB) {
        super(1, nodeA, nodeB);
    }
}

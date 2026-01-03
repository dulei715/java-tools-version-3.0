package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.DirectedEdge;

import java.util.Map;

public class SimpleDirectedGraph extends Graph<DirectedEdge> {
    public static final Boolean PositiveDirection = true;
    public static final Boolean NegativeDirection = false;

    @Override
    public Map<Node, Map<Node, DirectedEdge>> getAdjacentMap() {
        return null;
    }


    //    @Deprecated // 尽量不用，因为默认支持无向图
//    protected void addEdgeWithSingleMap(Edge edge, Boolean directionFlag) {
//        Node nodeA = edge.getNodeA();
//        Node nodeB = edge.getNodeB();
//        if (nodeA == null || nodeB == null) {
//            throw new RuntimeException("There is a null node!");
//        }
//        if (!directionFlag) {
//            Node tempNode = nodeA;
//            nodeA = nodeB;
//            nodeB = tempNode;
//        }
//        this.nodeSet.add(nodeA);
//        this.nodeSet.add(nodeB);
//        MapUtils.addTwoIndexValue(this.adjacentMap, nodeA, nodeB, edge);
//        this.singleDirectEdgeSet.add(edge);
//    }


}

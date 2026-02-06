package cn.edu.dll.struct.graph.utils;


import cn.edu.dll.constant_values.ConstantValues;
import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;

import java.util.*;

public class GraphUtils {

    public static <T extends Edge> T getEdge(Graph<T> graph, Node nodeA, Node nodeB) {
        Map<Node, T> neighboring = graph.getNeighboring(nodeA);
        if (neighboring == null) {
            return null;
        }
        return neighboring.get(nodeB);
    }

    public static <T extends Edge> Set<T> getEdgeSetByNodeSetAdjacent(Set<Node> nodeSet, Map<Node, Map<Node, T>> adjacentMap) {
        Map<Node, T> tempInnerMap;
        T tempEdge;
        Set<T> edgeSet = new HashSet<>();
        for (Node leftNode : nodeSet) {
            tempInnerMap = adjacentMap.get(leftNode);
            for (Node rightNode : nodeSet) {
                tempEdge = tempInnerMap.get(rightNode);
                edgeSet.add(tempEdge);
            }
        }
        return edgeSet;
    }

    public static void showGraph(Graph<? extends Edge> graph) {
        Node outerNode, innerNode;
        Map<Node, ? extends Edge> innerMap;
        Edge edge;
        graph.getAdjacentMap();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Node, ? extends Map<Node, ? extends Edge>> nodeMapEntry : graph.getAdjacentMap().entrySet()) {
            outerNode = nodeMapEntry.getKey();
            innerMap = nodeMapEntry.getValue();
            stringBuilder.append(String.format("node(%d): ", outerNode.getNodeID()));
            int i = 0;
            int size = innerMap.size();
            for (Map.Entry<Node, ? extends Edge> innerEntry : innerMap.entrySet()) {
                ++i;
                innerNode = innerEntry.getKey();
                edge = innerEntry.getValue();
                if (i != size) {
                    stringBuilder.append(String.format("node(%d)->edge(%.2f); ", innerNode.getNodeID(), edge.getValue()));
                } else {
                    stringBuilder.append(String.format("node(%d)->edge(%.2f)", innerNode.getNodeID(), edge.getValue())).append(ConstantValues.LINE_SPLIT);
                }
            }
        }
        System.out.print(stringBuilder);
    }
}

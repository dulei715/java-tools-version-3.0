package cn.edu.dll.struct.graph.utils;


import cn.edu.dll.constant_values.ConstantValues;
import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;

import java.util.*;

public class GraphUtils {

    public static <V extends Number & Comparable<V>, E extends Edge<V>> E getEdge(Graph<V, E> graph, Node nodeA, Node nodeB) {
        Map<Node, E> neighboring = graph.getNeighboring(nodeA);
        if (neighboring == null) {
            return null;
        }
        return neighboring.get(nodeB);
    }

    public static <V extends Number & Comparable<V>, E extends Edge<V>> Set<E> getEdgeSetByNodeSetAdjacent(Set<Node> nodeSet, Map<Node, Map<Node, E>> adjacentMap) {
        Map<Node, E> tempInnerMap;
        E tempEdge;
        Set<E> edgeSet = new HashSet<>();
        for (Node leftNode : nodeSet) {
            tempInnerMap = adjacentMap.get(leftNode);
            for (Node rightNode : nodeSet) {
                tempEdge = tempInnerMap.get(rightNode);
                edgeSet.add(tempEdge);
            }
        }
        return edgeSet;
    }

    public static <E extends Edge<Double>> void edgeMultiple(Graph<Double, E> weightedGraph, Double factor) {
        Set<E> edgeSet = weightedGraph.getEdgeSet();
        for (E edge : edgeSet) {
            edge.setValue(edge.getValue() * factor);
        }
    }

    public static <V extends Number & Comparable<V>, E extends Edge<V>>
    void showGraph(Graph<V, E> graph) {
        Node outerNode, innerNode;
        Map<Node, E> innerMap;
        E edge;
        graph.getAdjacentMap();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Node, Map<Node, E>> nodeMapEntry : graph.getAdjacentMap().entrySet()) {
            outerNode = nodeMapEntry.getKey();
            innerMap = nodeMapEntry.getValue();
            stringBuilder.append(String.format("node(%d): ", outerNode.getNodeID()));
            int i = 0;
            int size = innerMap.size();
            for (Map.Entry<Node, E> innerEntry : innerMap.entrySet()) {
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

package cn.edu.dll.struct.graph.utils;


import cn.edu.dll.constant_values.ConstantValues;
import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;

import java.util.*;

public class GraphUtils {

    /**
     * 获取两节点之间的边
     * @param graph
     * @param nodeA
     * @param nodeB
     * @return
     * @param <V>
     * @param <E>
     */
    public static <V extends Number & Comparable<V>, N extends Node, E extends Edge<V>> E getEdge(Graph<V, N, E> graph, N nodeA, N nodeB) {
        Map<N, E> neighboring = graph.getNeighboring(nodeA);
        if (neighboring == null) {
            return null;
        }
        return neighboring.get(nodeB);
    }

    /**
     * 后去给定店集nodeSet内部的边的集合
     * @param nodeSet
     * @param adjacentMap
     * @return
     * @param <V>
     * @param <E>
     */
    public static <V extends Number & Comparable<V>, N extends Node, E extends Edge<V>> Set<E> getEdgeSetByNodeSetAdjacent(Set<N> nodeSet, Map<N, Map<N, E>> adjacentMap) {
        Map<N, E> tempInnerMap;
        E tempEdge;
        Set<E> edgeSet = new HashSet<>();
        for (N leftNode : nodeSet) {
            tempInnerMap = adjacentMap.get(leftNode);
            for (N rightNode : nodeSet) {
                tempEdge = tempInnerMap.get(rightNode);
                edgeSet.add(tempEdge);
            }
        }
        return edgeSet;
    }

    /**
     * 将给定的图的每个边权乘上factor
     * @param weightedGraph
     * @param factor
     */
    public static <N extends Node, E extends Edge<Double>> void edgeMultiple(Graph<Double, N, E> weightedGraph, Double factor) {
        Set<E> edgeSet = weightedGraph.getEdgeSet();
        for (E edge : edgeSet) {
            edge.setValue(edge.getValue() * factor);
        }
    }

    public static <V extends Number & Comparable<V>, N extends Node, E extends Edge<V>> void showGraph(Graph<V, N, E> graph) {
        N outerNode, innerNode;
        Map<N, E> innerMap;
        E edge;
        graph.getAdjacentMap();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<N, Map<N, E>> nodeMapEntry : graph.getAdjacentMap().entrySet()) {
            outerNode = nodeMapEntry.getKey();
            innerMap = nodeMapEntry.getValue();
            stringBuilder.append(String.format("node(%d): ", outerNode.getNodeID()));
            int i = 0;
            int size = innerMap.size();
            for (Map.Entry<N, E> innerEntry : innerMap.entrySet()) {
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

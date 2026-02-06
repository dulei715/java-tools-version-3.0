package cn.edu.dll.struct.graph;

import java.util.*;
import java.util.function.BinaryOperator;

public abstract class Graph <V extends Number & Comparable<V>, E extends Edge<V>> {
    protected final BinaryOperator<V> valueAdder;


    protected Set<Node> nodeSet;

    public Graph(BinaryOperator<V> valueAdder) {
        this.valueAdder = Objects.requireNonNull(valueAdder);
        this.nodeSet = new HashSet<>();
    }

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    public abstract Map<Node, Map<Node, E>> getAdjacentMap();

    public abstract Map<Node, E> getAdjacent(Node node);

    public abstract Set<E> getEdgeSet();


    public E getEdge(Node nodeA, Node nodeB) {
        Map<Node, Map<Node, E>> adjacentMap = this.getAdjacentMap();
        Map<Node, E> innerMap = adjacentMap.get(nodeA);
        Objects.requireNonNull(innerMap);
        return innerMap.get(nodeB);
    }

    public abstract Map<Node, E> getNeighboring(Node node);

    /**
     * 获取给定节点 node 的邻接边权重之和
     * 如果是无权图，每个边的权重是1，等价于计算边的个数
     * @param node
     * @return
     */
    public V getAdjacentEdgeValueSum(Node node) {
        Map<Node, E> innerMap = this.getAdjacentMap().get(node);
        Objects.requireNonNull(innerMap);
//        Double result = 0D;
//        for (E edge : innerMap.values()) {
//            result += edge.getValue();
//        }
//        return result;
        return innerMap.values().stream()
                .map(Edge::getValue)
                .reduce(valueAdder)
                .orElseThrow(() -> new NoSuchElementException("No edges"));
    }

    /**
     * 获取给定节点node 到 limitedNodeCollection 中节点的边（直连）权重之和
     * 如果是无权图，每个边的权重是1，等价于计算边的个数
     * @param node
     * @param limitedNodeCollection
     * @return
     */
    public V getAdjacentEdgeValueSum(Node node, Collection<Node> limitedNodeCollection) {
        Map<Node, E> innerMap = this.getAdjacentMap().get(node);
        Objects.requireNonNull(innerMap);

//        Double result = 0D;
//        Node adjacentNode;
//        for (Map.Entry<Node, E> entry : innerMap.entrySet()) {
//            adjacentNode = entry.getKey();
//            if (limitedNodeCollection.contains(adjacentNode)) {
//                result += entry.getValue().getValue();
//            }
//        }
//        return result;
        return innerMap.entrySet().stream()
                .filter(entry -> limitedNodeCollection.contains(entry.getKey()))
                .map(entry -> entry.getValue().getValue())
                .reduce(valueAdder)
                .orElseThrow(() -> new NoSuchElementException("No edges from" + node + "to the given node collection"));
    }

}

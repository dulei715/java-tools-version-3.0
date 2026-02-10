package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.utils.GraphUtils;

import java.util.*;
import java.util.function.BinaryOperator;

public abstract class UndirectedGraph<V extends Number & Comparable<V>, N extends Node, E extends UndirectedEdge<V, N>> extends Graph<V, N, E> {

    protected Set<E> edgeSet;
    // 所有的edge只出现一次
    protected Map<N, Map<N, E>> adjacentMap;

    public UndirectedGraph(BinaryOperator<V> valueAdder) {
        super(valueAdder);
        this.edgeSet = new HashSet<>();
        this.adjacentMap = new HashMap<>();
    }



    public UndirectedGraph(BinaryOperator<V> valueAdder, Set<N> nodeSet, Map<N, Map<N, E>> adjacentMap) {
        super(valueAdder);
        this.nodeSet = nodeSet;
        this.adjacentMap = adjacentMap;
        this.edgeSet = GraphUtils.getEdgeSetByNodeSetAdjacent(this.nodeSet, this.adjacentMap);
    }


    public void addNode(N node) {
        super.nodeSet.add(node);
        this.adjacentMap.computeIfAbsent(node, k -> new HashMap<>());
    }

    public void addNode(Set<N> nodeSet) {
        super.nodeSet.addAll(nodeSet);
        for (N node : nodeSet) {
            this.adjacentMap.computeIfAbsent(node, k -> new HashMap<>());
        }
    }

    public void addEdge(E edge) {
        Iterator<N> iterator = edge.getNodeSet().iterator();
        N nodeA = iterator.next();
        N nodeB = iterator.next();
        if (nodeA == null || nodeB == null) {
            throw new RuntimeException("There is a null node!");
        }
        this.nodeSet.add(nodeA);
        this.nodeSet.add(nodeB);

        MapUtils.addTwoIndexValue(this.adjacentMap, nodeA, nodeB, edge);
        MapUtils.addTwoIndexValue(this.adjacentMap, nodeB, nodeA, edge);

        this.edgeSet.add(edge);

    }

    public void addEdge(Collection<E> edgeCollection) {
        for (E edge : edgeCollection) {
            addEdge(edge);
        }
    }

    @Override
    public Map<N, E> getNeighboring(N node) {
        return this.adjacentMap.get(node);
    }


    public abstract void combineGraph(UndirectedGraph<V, N, E> graph);

    public abstract void combineGraph(UndirectedGraph<V, N, E> graph, final Set<N> limitNodeSet);


    /**
     * 用给定的totalGraph补全本Graph，要求totalGraph和本Graph同类型
     * @param totalGraph
     */
    public void complementGraph(final UndirectedGraph<V, N, E> totalGraph) {
        Set<N> complementNodeSet = new HashSet<>(totalGraph.nodeSet);
        complementNodeSet.removeAll(this.nodeSet);
        Map<N, E> adjacent;
        Set<E> extraEdgeSet = new HashSet<>();
        E edge;
        for (N extraNode : complementNodeSet) {
            this.addNode(extraNode);
            adjacent = totalGraph.getAdjacent(extraNode);
            for (Map.Entry<N, E> entry : adjacent.entrySet()) {
                edge = entry.getValue();
                if (extraEdgeSet.contains(edge)) {
                    continue;
                }
                extraEdgeSet.add(edge);
            }
        }
    }


    @Override
    public Set<E> getEdgeSet() {
        return this.edgeSet;
    }

    @Override
    public Map<N, Map<N, E>> getAdjacentMap() {
        return adjacentMap;
    }

    @Override
    public Map<N, E> getAdjacent(N node) {
        return this.adjacentMap.get(node);
    }


}

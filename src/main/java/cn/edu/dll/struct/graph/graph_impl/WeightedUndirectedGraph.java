package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.impls.WeightedUndirectedEdge;
import cn.edu.dll.struct.graph.node_impl.SimpleNode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WeightedUndirectedGraph<N extends Node> extends UndirectedGraph<Double, N, WeightedUndirectedEdge<N>> {

    public WeightedUndirectedGraph() {
        super(Double::sum);
    }

    public WeightedUndirectedGraph(Set<N> nodeSet, Map<N, Map<N, WeightedUndirectedEdge<N>>> adjacentMap) {
        super(Double::sum, nodeSet, adjacentMap);
    }

    /**
     * 将给定的graph合并到本graph中，边权重相加
     * @param graph
     */



    public void combineGraph(UndirectedGraph<Double, N, WeightedUndirectedEdge<N>> graph) {
        Set<WeightedUndirectedEdge<N>> addedEdgeSet = graph.getEdgeSet();
        Map<WeightedUndirectedEdge<N>, WeightedUndirectedEdge<N>> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<WeightedUndirectedEdge<N>> realAddedEdgeSet = new HashSet<>();
        UndirectedEdge<Double, N> originalEdge;
        for (WeightedUndirectedEdge<N> edge : addedEdgeSet) {
            // 用新加入的edg定位原始的edge
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            } else {
                originalEdge.setValue(originalEdge.getValue() + edge.getValue());
            }
        }
        for (WeightedUndirectedEdge<N> newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
    }

    protected void increaseEdgeValue(N nodeA, N nodeB, Double increasedValue) {
        // 构建时保证 (nodeA, nodeB) 和 (nodeB, nodeA) 指向相同的对象，因而此处只用修改一处值
        Map<N, WeightedUndirectedEdge<N>> innerMap = this.adjacentMap.get(nodeA);
        WeightedUndirectedEdge<N> tempEdge;
        if (innerMap == null || innerMap.get(nodeB) == null) {
            tempEdge = new WeightedUndirectedEdge<>(increasedValue, nodeA, nodeB);
            MapUtils.addTwoIndexValue(this.adjacentMap, nodeA, nodeB, tempEdge);
            this.edgeSet.add(tempEdge);
            return;
        }
        UndirectedEdge<Double, N> edge = innerMap.get(nodeB);
        edge.setValue(edge.getValue() + increasedValue);
    }

    /**
     * 将给定graph合并到本graph中，权重相加，limitNodeSet
     * 如果limitNodeSet里的节点没有在graph中出现，则添加孤立的节点
     * @param graph
     * @param limitNodeSet
     */
    public void combineGraph(UndirectedGraph<Double, N, WeightedUndirectedEdge<N>> graph, final Set<N> limitNodeSet) {
        Set<WeightedUndirectedEdge<N>> addedEdgeSet = graph.getEdgeSet();
        Map<WeightedUndirectedEdge<N>, WeightedUndirectedEdge<N>> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<N> remainLimitNodeSet = new HashSet<>(limitNodeSet), currentEdgeNodeSet;
        Set<WeightedUndirectedEdge<N>> realAddedEdgeSet = new HashSet<>();
        UndirectedEdge<Double, N> originalEdge;
        for (WeightedUndirectedEdge<N> edge : addedEdgeSet) {
            currentEdgeNodeSet = edge.getNodeSet();
            if (!limitNodeSet.containsAll(currentEdgeNodeSet)) {
                continue;
            }
            remainLimitNodeSet.removeAll(currentEdgeNodeSet);
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            } else {
                originalEdge.setValue(originalEdge.getValue() + edge.getValue());
            }
        }
        for (WeightedUndirectedEdge<N> newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
        for (N node : remainLimitNodeSet) {
            this.addNode(node);
        }
    }
}

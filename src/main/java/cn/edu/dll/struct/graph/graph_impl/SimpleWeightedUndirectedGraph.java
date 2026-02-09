package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.WeightedUndirectedEdge;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleWeightedUndirectedGraph extends SimpleUndirectedGraph<Double, WeightedUndirectedEdge>{

    public SimpleWeightedUndirectedGraph() {
        super(Double::sum);
    }

    public SimpleWeightedUndirectedGraph(Set<Node> nodeSet, Map<Node, Map<Node, WeightedUndirectedEdge>> adjacentMap) {
        super(Double::sum, nodeSet, adjacentMap);
    }

    /**
     * 将给定的graph合并到本graph中，边权重相加
     * @param graph
     */



    public void combineGraph(SimpleUndirectedGraph<Double, WeightedUndirectedEdge> graph) {
        Set<WeightedUndirectedEdge> addedEdgeSet = graph.getEdgeSet();
        Map<WeightedUndirectedEdge, WeightedUndirectedEdge> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<WeightedUndirectedEdge> realAddedEdgeSet = new HashSet<>();
        UndirectedEdge<Double> originalEdge;
        for (WeightedUndirectedEdge edge : addedEdgeSet) {
            // 用新加入的edg定位原始的edge
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            } else {
                originalEdge.setValue(originalEdge.getValue() + edge.getValue());
            }
        }
        for (WeightedUndirectedEdge newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
    }

    protected void increaseEdgeValue(Node nodeA, Node nodeB, Double increasedValue) {
        // 构建时保证 (nodeA, nodeB) 和 (nodeB, nodeA) 指向相同的对象，因而此处只用修改一处值
        Map<Node, WeightedUndirectedEdge> innerMap = this.adjacentMap.get(nodeA);
        WeightedUndirectedEdge tempEdge;
        if (innerMap == null || innerMap.get(nodeB) == null) {
            tempEdge = new WeightedUndirectedEdge(increasedValue, nodeA, nodeB);
            MapUtils.addTwoIndexValue(this.adjacentMap, nodeA, nodeB, tempEdge);
            this.edgeSet.add(tempEdge);
            return;
        }
        UndirectedEdge<Double> edge = innerMap.get(nodeB);
        edge.setValue(edge.getValue() + increasedValue);
    }

    /**
     * 将给定graph合并到本graph中，权重相加，limitNodeSet
     * 如果limitNodeSet里的节点没有在graph中出现，则添加孤立的节点
     * @param graph
     * @param limitNodeSet
     */
    public void combineGraph(SimpleUndirectedGraph<Double, WeightedUndirectedEdge> graph, final Set<Node> limitNodeSet) {
        Set<WeightedUndirectedEdge> addedEdgeSet = graph.getEdgeSet();
        Map<WeightedUndirectedEdge, WeightedUndirectedEdge> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<Node> remainLimitNodeSet = new HashSet<>(limitNodeSet), currentEdgeNodeSet;
        Set<WeightedUndirectedEdge> realAddedEdgeSet = new HashSet<>();
        UndirectedEdge<Double> originalEdge;
        for (WeightedUndirectedEdge edge : addedEdgeSet) {
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
        for (WeightedUndirectedEdge newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
        for (Node node : remainLimitNodeSet) {
            this.addNode(node);
        }
    }
}

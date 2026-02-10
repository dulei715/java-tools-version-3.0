package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.impls.UnweightedUndirectedEdge;
import cn.edu.dll.struct.graph.node_impl.SimpleNode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnweightedUndirectedGraph<N extends Node> extends UndirectedGraph<Integer, N, UnweightedUndirectedEdge<N>> {
    public UnweightedUndirectedGraph() {
        super(Integer::sum);
    }

    public UnweightedUndirectedGraph(Set<N> nodeSet, Map<N, Map<N, UnweightedUndirectedEdge<N>>> adjacentMap) {
        super(Integer::sum, nodeSet, adjacentMap);
    }

    @Override
    public void combineGraph(UndirectedGraph<Integer, N, UnweightedUndirectedEdge<N>> graph) {
        Set<UnweightedUndirectedEdge<N>> addedEdgeSet = graph.getEdgeSet();
        Map<UnweightedUndirectedEdge<N>, UnweightedUndirectedEdge<N>> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<UnweightedUndirectedEdge<N>> realAddedEdgeSet = new HashSet<>();
        UnweightedUndirectedEdge<N> originalEdge;
        for (UnweightedUndirectedEdge<N> edge : addedEdgeSet) {
            // 用新加入的edg定位原始的edge
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            }
        }
        for (UnweightedUndirectedEdge<N> newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
    }

    @Override
    public void combineGraph(UndirectedGraph<Integer, N, UnweightedUndirectedEdge<N>> graph, Set<N> limitNodeSet) {
        Set<UnweightedUndirectedEdge<N>> addedEdgeSet = graph.getEdgeSet();
        Map<UnweightedUndirectedEdge<N>, UnweightedUndirectedEdge<N>> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<N> remainLimitNodeSet = new HashSet<>(limitNodeSet), currentEdgeNodeSet;
        Set<UnweightedUndirectedEdge<N>> realAddedEdgeSet = new HashSet<>();
        UnweightedUndirectedEdge<N> originalEdge;
        for (UnweightedUndirectedEdge<N> edge : addedEdgeSet) {
            currentEdgeNodeSet = edge.getNodeSet();
            if (!limitNodeSet.containsAll(currentEdgeNodeSet)) {
                continue;
            }
            remainLimitNodeSet.removeAll(currentEdgeNodeSet);
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            }
        }
        for (UnweightedUndirectedEdge<N> newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
        for (N node : remainLimitNodeSet) {
            this.addNode(node);
        }
    }
}

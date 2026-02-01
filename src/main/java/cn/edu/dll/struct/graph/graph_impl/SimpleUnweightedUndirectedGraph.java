package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.UnweightedUndirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.WeightedUndirectedEdge;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleUnweightedUndirectedGraph extends SimpleUndirectedGraph<UnweightedUndirectedEdge>{
    @Override
    public void combineGraph(SimpleUndirectedGraph<UnweightedUndirectedEdge> graph) {
        Set<UnweightedUndirectedEdge> addedEdgeSet = graph.getEdgeSet();
        Map<UnweightedUndirectedEdge, UnweightedUndirectedEdge> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<UnweightedUndirectedEdge> realAddedEdgeSet = new HashSet<>();
        UnweightedUndirectedEdge originalEdge;
        for (UnweightedUndirectedEdge edge : addedEdgeSet) {
            // 用新加入的edg定位原始的edge
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            }
        }
        for (UnweightedUndirectedEdge newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
    }

    @Override
    public void combineGraph(SimpleUndirectedGraph<UnweightedUndirectedEdge> graph, Set<Node> limitNodeSet) {
        Set<UnweightedUndirectedEdge> addedEdgeSet = graph.getEdgeSet();
        Map<UnweightedUndirectedEdge, UnweightedUndirectedEdge> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<Node> remainLimitNodeSet = new HashSet<>(limitNodeSet), currentEdgeNodeSet;
        Set<UnweightedUndirectedEdge> realAddedEdgeSet = new HashSet<>();
        UnweightedUndirectedEdge originalEdge;
        for (UnweightedUndirectedEdge edge : addedEdgeSet) {
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
        for (UnweightedUndirectedEdge newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
        for (Node node : remainLimitNodeSet) {
            this.addNode(node);
        }
    }
}

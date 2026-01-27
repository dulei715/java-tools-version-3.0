package cn.edu.dll.struct.graph;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.utils.GraphTools;

import java.util.*;

public abstract class Graph <T extends Edge> {
    protected Set<Node> nodeSet;

    public Graph() {
        this.nodeSet = new HashSet<>();
    }

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    public abstract Map<Node, Map<Node, T>> getAdjacentMap();

    public abstract Map<Node, T> getAdjacent(Node node);

    public abstract Set<T> getEdgeSet();

    public void edgeMultiple(Double factor) {
        Set<T> edgeSet = this.getEdgeSet();
        for (T edge : edgeSet) {
            edge.setValue(edge.getValue() * factor);
        }
    }

    public T getEdge(Node nodeA, Node nodeB) {
        Map<Node, Map<Node, T>> adjacentMap = this.getAdjacentMap();
        Map<Node, T> innerMap = adjacentMap.get(nodeA);
        Objects.requireNonNull(innerMap);
        return innerMap.get(nodeB);
    }


    public Double getAdjacentEdgeValueSum(Node node) {
        Map<Node, T> innerMap = this.getAdjacentMap().get(node);
        Objects.requireNonNull(innerMap);
        Double result = 0D;
        for (T edge : innerMap.values()) {
            result += edge.getValue();
        }
        return result;
    }

}

package cn.edu.dll.struct.graph;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.utils.GraphTools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Graph <T extends Edge> {
    protected Set<Node> nodeSet;

    public Graph() {
        this.nodeSet = new HashSet<>();
    }

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    public abstract Map<Node, Map<Node, T>> getAdjacentMap();

    public abstract Set<T> getEdgeSet();

}

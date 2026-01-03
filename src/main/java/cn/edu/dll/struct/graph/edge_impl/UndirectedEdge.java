package cn.edu.dll.struct.graph.edge_impl;

import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Node;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UndirectedEdge extends Edge {
    protected Set<Node> nodeSet = null;

    public UndirectedEdge(Double value) {
        super(value);
        this.nodeSet = new HashSet<>();
    }

    public UndirectedEdge(Double value, Node nodeA, Node nodeB) {
        super(value);
        this.nodeSet = new HashSet<>();
        this.nodeSet.add(nodeA);
        this.nodeSet.add(nodeB);
    }

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedEdge that = (UndirectedEdge) o;
        // 依赖于node之间的equals方法
        return Objects.equals(nodeSet, that.nodeSet);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nodeSet);
    }
}

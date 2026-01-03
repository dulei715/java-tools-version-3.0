package cn.edu.dll.struct.graph.edge_impl;

import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Node;

import java.util.Objects;

public class DirectedEdge extends Edge {

    protected Node startNode = null;
    protected Node endNode = null;

    public DirectedEdge(Double value, Node startNode, Node endNode) {
        this.value = value;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public DirectedEdge(Double value) {
        this.value = value;
    }



    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DirectedEdge that = (DirectedEdge) o;
        return Objects.equals(startNode, that.startNode) && Objects.equals(endNode, that.endNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startNode, endNode);
    }

}

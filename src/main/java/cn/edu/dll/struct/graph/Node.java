package cn.edu.dll.struct.graph;

import java.util.Objects;

public abstract class Node implements Comparable<Node> {
    protected Integer nodeID = null;

    public Node(Integer nodeID) {
        this.nodeID = nodeID;
    }

    public Integer getNodeID() {
        return nodeID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nodeID, node.nodeID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nodeID);
    }


    @Override
    public int compareTo(Node nodeB) {
        return this.nodeID.compareTo(nodeB.nodeID);
    }
}

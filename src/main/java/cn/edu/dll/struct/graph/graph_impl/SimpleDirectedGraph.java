package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.DirectedEdge;
import cn.edu.dll.struct.graph.utils.GraphTools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleDirectedGraph extends Graph<DirectedEdge> {
    public static final Boolean PositiveDirection = true;
    public static final Boolean NegativeDirection = false;

    protected Set<DirectedEdge> edgeSet;
    protected Map<Node, Map<Node, DirectedEdge>> adjacentMap;

    public SimpleDirectedGraph() {
        this.edgeSet = new HashSet<>();
        this.adjacentMap = new HashMap<>();
    }

    public SimpleDirectedGraph(Set<Node> nodeSet, Map<Node, Map<Node, DirectedEdge>> adjacentMap) {
        this.adjacentMap = adjacentMap;
        this.edgeSet = GraphTools.getEdgeSetByNodeSetAdjacent(this.nodeSet, this.adjacentMap);
    }

    @Override
    public Map<Node, Map<Node, DirectedEdge>> getAdjacentMap() {
        return this.adjacentMap;
    }

    @Override
    public Set<DirectedEdge> getEdgeSet() {
        return this.edgeSet;
    }

    protected void increaseEdgeValue(Node startNode, Node endNode, Double increasedValue) {
        Map<Node, DirectedEdge> innerMap = this.adjacentMap.get(startNode);
        DirectedEdge tempEdge;
        if (innerMap == null || innerMap.get(endNode) == null) {
            tempEdge = new DirectedEdge(increasedValue, startNode, endNode);
            MapUtils.addTwoIndexValue(this.adjacentMap, startNode, endNode, tempEdge);
            this.edgeSet.add(tempEdge);
            return;
        }
        DirectedEdge edge = innerMap.get(endNode);
        edge.setValue(edge.getValue() + increasedValue);
    }



    public void addEdge(DirectedEdge edge) {
        Node startNode = edge.getStartNode();
        Node endNode = edge.getEndNode();
        if (startNode == null || endNode == null) {
            throw new RuntimeException("There is a null node!");
        }
        this.nodeSet.add(startNode);
        this.nodeSet.add(endNode);
        MapUtils.addTwoIndexValue(this.adjacentMap, startNode, endNode, edge);
        this.edgeSet.add(edge);
    }

    public void combineGraph(SimpleDirectedGraph graph) {
        Set<DirectedEdge> addedEdgeSet = graph.getEdgeSet();
        Map<DirectedEdge, DirectedEdge> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<DirectedEdge> realAddedEdgeSet = new HashSet<>();
        DirectedEdge originalEdge;
        for (DirectedEdge edge : addedEdgeSet) {
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            } else {
                originalEdge.setValue(originalEdge.getValue() + edge.getValue());
            }
        }
        for (DirectedEdge newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
    }

}

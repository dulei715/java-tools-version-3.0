package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.DirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.utils.GraphUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;

public class SimpleDirectedGraph<V extends Number & Comparable<V>, E extends DirectedEdge<V>> extends Graph<V, E> {
    public static final Boolean PositiveDirection = true;
    public static final Boolean NegativeDirection = false;

    protected Set<E> edgeSet;
    protected Map<Node, Map<Node, E>> adjacentMap;

    public SimpleDirectedGraph(BinaryOperator<V> valueAdder) {
        super(valueAdder);
        this.edgeSet = new HashSet<>();
        this.adjacentMap = new HashMap<>();
    }

    public SimpleDirectedGraph(BinaryOperator<V> valueAdder, Set<Node> nodeSet, Map<Node, Map<Node, E>> adjacentMap) {
        super(valueAdder);
        this.adjacentMap = adjacentMap;
        this.nodeSet = nodeSet;
        this.edgeSet = GraphUtils.getEdgeSetByNodeSetAdjacent(this.nodeSet, this.adjacentMap);
    }

    @Override
    public Map<Node, Map<Node, E>> getAdjacentMap() {
        return this.adjacentMap;
    }

    @Override
    public Map<Node, E> getAdjacent(Node node) {
        return this.adjacentMap.get(node);
    }

    @Override
    public Set<E> getEdgeSet() {
        return this.edgeSet;
    }

    @Override
    public Map<Node, E> getNeighboring(Node node) {
        return this.adjacentMap.get(node);
    }

//    protected void increaseEdgeValue(Node startNode, Node endNode, Double increasedValue) {
//        Map<Node, E> innerMap = this.adjacentMap.get(startNode);
//        E tempEdge;
//        if (innerMap == null || innerMap.get(endNode) == null) {
//            tempEdge = new DirectedEdge(super.valueAdder, increasedValue, startNode, endNode);
//            MapUtils.addTwoIndexValue(this.adjacentMap, startNode, endNode, tempEdge);
//            this.edgeSet.add(tempEdge);
//            return;
//        }
//        DirectedEdge edge = innerMap.get(endNode);
//        edge.setValue(edge.getValue() + increasedValue);
//    }



    public void addEdge(E edge) {
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

    public void combineGraph(SimpleDirectedGraph<V, E> graph) {
        Set<E> addedEdgeSet = graph.getEdgeSet();
        Map<E, E> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<E> realAddedEdgeSet = new HashSet<>();
        E originalEdge;
        for (E edge : addedEdgeSet) {
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            } else {
//                originalEdge.setValue(originalEdge.getValue() + edge.getValue());
                originalEdge.setValue(super.valueAdder.apply(originalEdge.getValue(), edge.getValue()));
            }
        }
        for (E newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
    }

}

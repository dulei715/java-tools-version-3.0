package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.utils.GraphTools;

import java.util.*;

public class SimpleUndirectedGraph extends Graph<UndirectedEdge> {


    protected Set<UndirectedEdge> edgeSet;
    // 所有的edge只出现一次
    protected Map<Node, Map<Node, UndirectedEdge>> adjacentMap;

    public SimpleUndirectedGraph() {
        this.edgeSet = new HashSet<>();
        this.adjacentMap = new HashMap<>();
    }



    public SimpleUndirectedGraph(Set<Node> nodeSet, Map<Node, Map<Node, UndirectedEdge>> adjacentMap) {
        this.adjacentMap = adjacentMap;
        this.edgeSet = GraphTools.getEdgeSetByNodeSetAdjacent(this.nodeSet, this.adjacentMap);
    }



    protected void increaseEdgeValue(Node nodeA, Node nodeB, Double increasedValue) {
        // 构建时保证 (nodeA, nodeB) 和 (nodeB, nodeA) 指向相同的对象，因而此处只用修改一处值
        Map<Node, UndirectedEdge> innerMap = this.adjacentMap.get(nodeA);
        UndirectedEdge tempEdge;
        if (innerMap == null || innerMap.get(nodeB) == null) {
            tempEdge = new UndirectedEdge(increasedValue, nodeA, nodeB);
            MapUtils.addTwoIndexValue(this.adjacentMap, nodeA, nodeB, tempEdge);
            this.edgeSet.add(tempEdge);
            return;
        }
        UndirectedEdge edge = innerMap.get(nodeB);
        edge.setValue(edge.getValue() + increasedValue);
    }

    public void addEdge(UndirectedEdge edge) {
        Iterator<Node> iterator = edge.getNodeSet().iterator();
        Node nodeA = iterator.next();
        Node nodeB = iterator.next();
        if (nodeA == null || nodeB == null) {
            throw new RuntimeException("There is a null node!");
        }
        this.nodeSet.add(nodeA);
        this.nodeSet.add(nodeB);

        MapUtils.addTwoIndexValue(this.adjacentMap, nodeA, nodeB, edge);
        MapUtils.addTwoIndexValue(this.adjacentMap, nodeB, nodeA, edge);

        this.edgeSet.add(edge);

    }

    public Map<Node, UndirectedEdge> getNeighboring(Node node) {
        return this.adjacentMap.get(node);
    }


    /**
     * 将给定的graph合并到本graph中，边权重相加
     * @param graph
     */

    public void combineGraph(SimpleUndirectedGraph graph) {
        Set<UndirectedEdge> addedEdgeSet = graph.getEdgeSet();
        Map<UndirectedEdge, UndirectedEdge> currentEdgeSelfMap = MapUtils.getSelfMap(this.getEdgeSet());
        Set<UndirectedEdge> realAddedEdgeSet = new HashSet<>();
        UndirectedEdge originalEdge;
        for (UndirectedEdge edge : addedEdgeSet) {
            originalEdge = currentEdgeSelfMap.get(edge);
            if (originalEdge == null) {
                realAddedEdgeSet.add(edge);
            } else {
                originalEdge.setValue(originalEdge.getValue() + edge.getValue());
            }
        }
        for (UndirectedEdge newEdge : realAddedEdgeSet) {
            this.addEdge(newEdge);
        }
    }



    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    @Override
    public Set<UndirectedEdge> getEdgeSet() {
        return this.edgeSet;
    }

    @Override
    public Map<Node, Map<Node, UndirectedEdge>> getAdjacentMap() {
        return adjacentMap;
    }


}

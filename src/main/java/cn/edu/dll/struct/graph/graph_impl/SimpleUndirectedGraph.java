package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.constant_values.ConstantValues;
import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.utils.GraphTools;

import java.util.*;

public class SimpleUndirectedGraph extends Graph<UndirectedEdge> {

    protected Set<Node> nodeSet;
    protected Set<UndirectedEdge> edgeSet;
    protected Map<Node, Map<Node, UndirectedEdge>> adjacentMap;

    public SimpleUndirectedGraph() {
        this.nodeSet = new HashSet<>();
        this.edgeSet = new HashSet<>();
        this.adjacentMap = new HashMap<>();
    }



    public SimpleUndirectedGraph(Set<Node> nodeSet, Map<Node, Map<Node, UndirectedEdge>> adjacentMap) {
        this.nodeSet = nodeSet;
        this.adjacentMap = adjacentMap;
        this.edgeSet = GraphTools.getEdgeSetByNodeSetAdjacent(this.nodeSet, this.adjacentMap);
    }



    protected void increaseEdgeValue(Node nodeA, Node nodeB, Double increasedValue) {
        // 构建时保证 (nodeA, nodeB) 和 (nodeB, nodeA) 指向相同的对象，因而此处只用修改一处值
        Map<Node, UndirectedEdge> innerMap = this.adjacentMap.get(nodeA);
        if (innerMap == null || innerMap.get(nodeB) == null) {
            MapUtils.addTwoIndexValue(this.adjacentMap, nodeA, nodeB, new UndirectedEdge(increasedValue, nodeA, nodeB));
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


    /**
     * 将给定的graph合并到本graph中，边权重相加
     * // todo: 有问题，现在addEdge中调换出入点（A-B 和 B-A）的映射是同一个边，这样会导致增加两次
     * @param graph
     */
//    public void combineGraph(SimpleUndirectedGraph graph) {
//        Map<Node, Map<Node, UndirectedEdge>> addedAdjacentMap = graph.adjacentMap;
//        Node outerNode, innerNode, thisOuterNode, thisInnerNode;
//        Map<Node, UndirectedEdge> innerMap, thisInnerMap;
//        Edge addedEdge;
//        for (Map.Entry<Node, Map<Node, UndirectedEdge>> entry : addedAdjacentMap.entrySet()) {
//            outerNode = entry.getKey();
//            innerMap = entry.getValue();
//
//            for (Map.Entry<Node, UndirectedEdge> nodeEdgeEntry : innerMap.entrySet()) {
//                innerNode = nodeEdgeEntry.getKey();
//                addedEdge = nodeEdgeEntry.getValue();
//                this.increaseEdgeValue(outerNode, innerNode, addedEdge.getValue());
//            }
//        }
//    }
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

    public Set<UndirectedEdge> getEdgeSet() {
        return this.edgeSet;
    }

    @Override
    public Map<Node, Map<Node, UndirectedEdge>> getAdjacentMap() {
        return adjacentMap;
    }


}

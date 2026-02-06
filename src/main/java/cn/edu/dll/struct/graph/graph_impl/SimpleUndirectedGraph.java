package cn.edu.dll.struct.graph.graph_impl;

import cn.edu.dll.map.MapUtils;
import cn.edu.dll.struct.graph.Graph;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.utils.GraphUtils;

import java.util.*;

public abstract class SimpleUndirectedGraph<T extends UndirectedEdge> extends Graph<T> {

    protected Set<T> edgeSet;
    // 所有的edge只出现一次
    protected Map<Node, Map<Node, T>> adjacentMap;

    public SimpleUndirectedGraph() {
        this.edgeSet = new HashSet<>();
        this.adjacentMap = new HashMap<>();
    }



    public SimpleUndirectedGraph(Set<Node> nodeSet, Map<Node, Map<Node, T>> adjacentMap) {
        this.nodeSet = nodeSet;
        this.adjacentMap = adjacentMap;
        this.edgeSet = GraphUtils.getEdgeSetByNodeSetAdjacent(this.nodeSet, this.adjacentMap);
    }





    public void addNode(Node node) {
        super.nodeSet.add(node);
        this.adjacentMap.computeIfAbsent(node, k -> new HashMap<>());
    }

    public void addNode(Set<Node> nodeSet) {
        super.nodeSet.addAll(nodeSet);
        for (Node node : nodeSet) {
            this.adjacentMap.computeIfAbsent(node, k -> new HashMap<>());
        }
    }

    public void addEdge(T edge) {
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

    public void addEdge(Collection<T> edgeCollection) {
        for (T edge : edgeCollection) {
            addEdge(edge);
        }
    }

    @Override
    public Map<Node, T> getNeighboring(Node node) {
        return this.adjacentMap.get(node);
    }


    public abstract void combineGraph(SimpleUndirectedGraph<T> graph);

    public abstract void combineGraph(SimpleUndirectedGraph<T> graph, final Set<Node> limitNodeSet);

    /**
     * 用给定的totalGraph补全本Graph，要求totalGraph和本Graph同类型
     * @param totalGraph
     */
    public void complementGraph(final SimpleUndirectedGraph<T> totalGraph) {
        Set<Node> complementNodeSet = new HashSet<>(totalGraph.nodeSet);
        complementNodeSet.removeAll(this.nodeSet);
        List<Node> complementNodeList = new ArrayList<>(complementNodeSet);
        Map<Node, T> adjacent;
        Set<T> extraEdgeSet = new HashSet<>();
        T edge;
        for (Node extraNode : complementNodeSet) {
            this.addNode(extraNode);
            adjacent = totalGraph.getAdjacent(extraNode);
            for (Map.Entry<Node, T> entry : adjacent.entrySet()) {
                edge = entry.getValue();
                if (extraEdgeSet.contains(edge)) {
                    continue;
                }
                extraEdgeSet.add(edge);
            }
        }

    }


    @Override
    public Set<T> getEdgeSet() {
        return this.edgeSet;
    }

    @Override
    public Map<Node, Map<Node, T>> getAdjacentMap() {
        return adjacentMap;
    }

    @Override
    public Map<Node, T> getAdjacent(Node node) {
        return this.adjacentMap.get(node);
    }


}

package cn.edu.dll.struct.graph.utils;

import cn.edu.dll.basic.ValidationUtil;
import cn.edu.dll.struct.graph.Community;
import cn.edu.dll.struct.graph.Edge;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;

import java.util.*;

public class CommunityUtils {
    public static <T extends Number> List<Community> getInitializedCommunityList(Integer communitySize, LinkedHashSet<T> communityIDSet) {
        ValidationUtil.requireEqual(communitySize, communityIDSet.size(), "The communitySize and communityIDSetSize are not equal!");
        List<Community> resultList = new ArrayList<>(communitySize);
        Iterator<T> communityIDIterator = communityIDSet.iterator();
        for (int i = 0; i < communitySize; ++i) {
            resultList.add(new Community(communityIDIterator.next().longValue()));
        }
        return resultList;
    }

    /**
     * 获取给定node到给定community之间的边集合
     * @param node
     * @param community
     * @param adjacentMap
     * @return
     * @param <E>
     */
    public static <E extends Edge<?>> Set<E> getEdgeSetFromNodeToCommunity(Node node, Community community, Map<Node, Map<Node, E>> adjacentMap) {
        Node neighboringNode;
        Set<E> resultEdgeSet = new HashSet<>();
        Map<Node, E> neighboring = adjacentMap.get(node);
        for (Map.Entry<Node, E> entry : neighboring.entrySet()) {
            neighboringNode = entry.getKey();
            if (!community.contains(neighboringNode)) {
                continue;
            }
            resultEdgeSet.add(entry.getValue());
        }
        return resultEdgeSet;
    }

    public static <E extends Edge<?>> Set<E> getUndirectedEdgeSetWithinCommunity(Community community, Map<Node, Map<Node, E>> adjacentMap) {
        Map<Node, E> innerAdjacentMap;
        Node currentNode, adjacentNode;
        E adjacentEdge;
        Set<Node> nodeSet = community.getNodeSet();
        Set<E> resultEdgeSet = new HashSet<>();
        for (Node node : nodeSet) {
            innerAdjacentMap = adjacentMap.get(node);
            for (Map.Entry<Node, E> entry : innerAdjacentMap.entrySet()) {
                adjacentNode = entry.getKey();
                if (community.contains(adjacentNode)) {
                    resultEdgeSet.add(entry.getValue());
                }
            }
        }
        return resultEdgeSet;
    }

    /**
     * 获取给定community collection中所有的社区间的边的集合
     * @param communityCollection
     * @param adjacentMap
     * @return
     * @param <E>
     */
    public static <E extends UndirectedEdge<?>> Set<E> getUndirectedEdgeSetBetweenAllCommunityPairs(Collection<Community> communityCollection, Map<Node, Map<Node, E>> adjacentMap) {
        List<Community> communityList = new ArrayList<>(communityCollection);
        int communitySize = communityList.size();
        Community communityA, communityB;
        Set<Node> nodeSet;
        Set<E> resultEdgeSet = new HashSet<>();
        for (int i = 0; i < communitySize; ++i) {
            communityA = communityList.get(i);
            nodeSet = communityA.getNodeSet();
            for (Node node : nodeSet) {
                for (int j = i + 1; j < communitySize; ++j) {
                    communityB = communityList.get(j);
                    resultEdgeSet.addAll(getEdgeSetFromNodeToCommunity(node, communityB, adjacentMap));
                }
            }
        }
        return resultEdgeSet;
    }


















}

package cn.edu.dll.struct.graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Community<N extends Node> implements Comparable<Community<N>> {
    protected Long communityID;
    protected Set<N> nodeSet;

    public Community(Long communityID) {
        this.communityID = communityID;
        this.nodeSet = new HashSet<>();
    }

    public Community(Long communityID, Set<N> nodeSet) {
        this(communityID);
        this.nodeSet.addAll(nodeSet);
    }

    public Community(Long communityID, N node) {
        this(communityID);
        this.nodeSet.add(node);
    }

    public void addNode(N node) {
        this.nodeSet.add(node);
    }

    public boolean remove(N node) {
        return this.nodeSet.remove(node);
    }

    public boolean contains(N node) {
        return this.nodeSet.contains(node);
    }

    public void combineCommunity(Community<N> community) {
        this.nodeSet.addAll(community.nodeSet);
    }

    public static <N extends Node> Community<N> getCombineCommunity(Long communityID, final Community<N> communityA, final Community<N> communityB) {
        Community<N> community = new Community<>(communityID);
        community.combineCommunity(communityA);
        community.combineCommunity(communityB);
        return community;
    }

    public Long getCommunityID() {
        return communityID;
    }

    public Set<N> getNodeSet() {
        return nodeSet;

    }


    @Override
    public String toString() {
        return "Community{" +
                "communityID=" + communityID +
                ", nodeSet=" + nodeSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Community<?> community = (Community<?>) o;
        return Objects.equals(communityID, community.communityID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(communityID);
    }

    @Override
    public int compareTo(Community community) {
        return this.communityID.compareTo(community.communityID);
    }
}

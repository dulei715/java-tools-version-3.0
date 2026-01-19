package cn.edu.dll.struct.graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Community implements Comparable<Community> {
    protected Long communityID;
    protected Set<Node> nodeSet;

    public Community(Long communityID) {
        this.communityID = communityID;
        this.nodeSet = new HashSet<>();
    }

    public Community(Long communityID, Set<Node> nodeSet) {
        this(communityID);
        this.nodeSet.addAll(nodeSet);
    }

    public Community(Long communityID, Node node) {
        this(communityID);
        this.nodeSet.add(node);
    }

    public void addNode(Node node) {
        this.nodeSet.add(node);
    }

    public boolean remove(Node node) {
        return this.nodeSet.remove(node);
    }

    public boolean contains(Node node) {
        return this.nodeSet.contains(node);
    }

    public void combineCommunity(Community community) {
        this.nodeSet.addAll(community.nodeSet);
    }

    public static Community getCombineCommunity(Long communityID, final Community communityA, final Community communityB) {
        Community community = new Community(communityID);
        community.combineCommunity(communityA);
        community.combineCommunity(communityB);
        return community;
    }

    public Long getCommunityID() {
        return communityID;
    }

    public Set<Node> getNodeSet() {
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
        Community community = (Community) o;
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

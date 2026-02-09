import cn.edu.dll.io.print.MyPrint;
import cn.edu.dll.struct.graph.Community;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.DirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.WeightedUndirectedEdge;
import cn.edu.dll.struct.graph.graph_impl.SimpleDirectedGraph;
import cn.edu.dll.struct.graph.graph_impl.SimpleUndirectedGraph;
import cn.edu.dll.struct.graph.graph_impl.SimpleWeightedUndirectedGraph;
import cn.edu.dll.struct.graph.node_impl.SimpleNode;
import cn.edu.dll.struct.graph.utils.GraphUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GraphTest {

    public static List<Node> nodeList;

    public static List<UndirectedEdge<Double>> undirectedEdgeList;

    public static List<DirectedEdge<Double>> directedEdgeList;

    @Before
    public void before() {
        nodeList = Arrays.asList(new Node[] {
                new SimpleNode(1), new SimpleNode(2),
                new SimpleNode(3), new SimpleNode(4),
                new SimpleNode(5), new SimpleNode(6),
                new SimpleNode(7), new SimpleNode(8)
        });
        undirectedEdgeList = Arrays.asList(new WeightedUndirectedEdge[] {
                new WeightedUndirectedEdge(21D, nodeList.get(0), nodeList.get(1)),
                new WeightedUndirectedEdge(6D, nodeList.get(1), nodeList.get(2)),
                new WeightedUndirectedEdge(33D, nodeList.get(2), nodeList.get(3)),
                new WeightedUndirectedEdge(10D, nodeList.get(3), nodeList.get(0)),

                new WeightedUndirectedEdge(13D, nodeList.get(0), nodeList.get(2)),
                new WeightedUndirectedEdge(4D, nodeList.get(2), nodeList.get(3)),
                new WeightedUndirectedEdge(76D, nodeList.get(3), nodeList.get(7)),
                new WeightedUndirectedEdge(17D, nodeList.get(7), nodeList.get(6))
        });
        directedEdgeList = Arrays.asList(new DirectedEdge[] {
                new DirectedEdge(21D, nodeList.get(0), nodeList.get(1)),
                new DirectedEdge(6D, nodeList.get(1), nodeList.get(2)),
                new DirectedEdge(33D, nodeList.get(2), nodeList.get(3)),
                new DirectedEdge(10D, nodeList.get(3), nodeList.get(0)),

                new DirectedEdge(13D, nodeList.get(0), nodeList.get(2)),
                new DirectedEdge(4D, nodeList.get(2), nodeList.get(3)),
                new DirectedEdge(76D, nodeList.get(3), nodeList.get(7)),
                new DirectedEdge(17D, nodeList.get(7), nodeList.get(6)),
                new DirectedEdge(17D, nodeList.get(3), nodeList.get(2))
        });
    }

    @Test
    public void undirectedGraphTest() {
        SimpleUndirectedGraph<Double, WeightedUndirectedEdge> graphA = new SimpleWeightedUndirectedGraph();
        for (int i = 0; i < 4; i++) {
            graphA.addEdge((WeightedUndirectedEdge) undirectedEdgeList.get(i));
        }
        SimpleUndirectedGraph<Double, WeightedUndirectedEdge> graphB = new SimpleWeightedUndirectedGraph();
        for (int i = 4; i < undirectedEdgeList.size(); i++) {
             graphB.addEdge((WeightedUndirectedEdge) undirectedEdgeList.get(i));

        }
        MyPrint.showSplitLine("*", 150);
        GraphUtils.showGraph(graphA);


        MyPrint.showSplitLine("*", 150);
        GraphUtils.showGraph(graphB);

        MyPrint.showSplitLine("*", 150);
        graphA.combineGraph(graphB);
        GraphUtils.showGraph(graphA);
    }
    @Test
    public void undirectedGraphWithLimitTest() {
        SimpleUndirectedGraph<Double, WeightedUndirectedEdge> graphA = new SimpleWeightedUndirectedGraph();
        for (int i = 0; i < 4; i++) {
            graphA.addEdge((WeightedUndirectedEdge) undirectedEdgeList.get(i));
        }
        SimpleUndirectedGraph<Double, WeightedUndirectedEdge> graphB = new SimpleWeightedUndirectedGraph();
        for (int i = 4; i < undirectedEdgeList.size(); i++) {
             graphB.addEdge((WeightedUndirectedEdge) undirectedEdgeList.get(i));

        }
        List<Node> limitNodeList = Arrays.asList(
                nodeList.get(0), nodeList.get(2), nodeList.get(3),
                new SimpleNode(12), new SimpleNode(15)
        );
        MyPrint.showSplitLine("*", 150);
        GraphUtils.showGraph(graphA);


        MyPrint.showSplitLine("*", 150);
        GraphUtils.showGraph(graphB);

        MyPrint.showSplitLine("*", 150);
        MyPrint.showCollection(limitNodeList, "; ");

        MyPrint.showSplitLine("*", 150);

        graphA.combineGraph(graphB, new HashSet<>(limitNodeList));
        GraphUtils.showGraph(graphA);
    }

    @Test
    public void directedGraphTest() {
        SimpleDirectedGraph<Double, DirectedEdge<Double>> graphA = new SimpleDirectedGraph<>(Double::sum);
        for (int i = 0; i < 4; i++) {
            graphA.addEdge(directedEdgeList.get(i));
        }
        SimpleDirectedGraph<Double, DirectedEdge<Double>> graphB = new SimpleDirectedGraph<>(Double::sum);
        for (int i = 4; i < directedEdgeList.size(); i++) {
            graphB.addEdge(directedEdgeList.get(i));

        }
        MyPrint.showSplitLine("*", 150);
        GraphUtils.showGraph(graphA);


        MyPrint.showSplitLine("*", 150);
        GraphUtils.showGraph(graphB);

        MyPrint.showSplitLine("*", 150);
        graphA.combineGraph(graphB);
        GraphUtils.showGraph(graphA);
    }

    @Test
    public void communityTest() {
        Community communityA = new Community(1L, nodeList.get(0));
        communityA.addNode(nodeList.get(1));
        Community communityB = new Community(2L, nodeList.get(2));
        communityB.addNode(nodeList.get(3));

        System.out.println(communityA);
        System.out.println(communityB);

        communityA.combineCommunity(communityB);
        System.out.println(communityA);
        System.out.println(communityB);
    }
    @Test
    public void communityTest2() {
        Community communityA = new Community(1L, nodeList.get(0));
        communityA.addNode(nodeList.get(1));
        Community communityB = new Community(2L, nodeList.get(2));
        communityB.addNode(nodeList.get(3));
        Community combineCommunity = Community.getCombineCommunity(3L, communityA, communityB);

        System.out.println(communityA);
        System.out.println(communityB);
        System.out.println(combineCommunity);
    }
}

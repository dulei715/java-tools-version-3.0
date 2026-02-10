import cn.edu.dll.io.print.MyPrint;
import cn.edu.dll.struct.graph.Community;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.DirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.impls.WeightedUndirectedEdge;
import cn.edu.dll.struct.graph.graph_impl.SimpleDirectedGraph;
import cn.edu.dll.struct.graph.graph_impl.UndirectedGraph;
import cn.edu.dll.struct.graph.graph_impl.WeightedUndirectedGraph;
import cn.edu.dll.struct.graph.node_impl.SimpleNode;
import cn.edu.dll.struct.graph.utils.EdgeUtils;
import cn.edu.dll.struct.graph.utils.GraphUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.BinaryOperator;

public class GraphTest {

    public static List<SimpleNode> nodeList;

    public static List<UndirectedEdge<Double, SimpleNode>> undirectedEdgeList;

    public static List<DirectedEdge<Double, SimpleNode>> directedEdgeList;

    @Before
    public void before() {
        nodeList = Arrays.asList(new SimpleNode[] {
                new SimpleNode(1), new SimpleNode(2),
                new SimpleNode(3), new SimpleNode(4),
                new SimpleNode(5), new SimpleNode(6),
                new SimpleNode(7), new SimpleNode(8)
        });
        undirectedEdgeList = Arrays.asList(new WeightedUndirectedEdge[] {
                new WeightedUndirectedEdge<>(21D, nodeList.get(0), nodeList.get(1)),
                new WeightedUndirectedEdge<>(6D, nodeList.get(1), nodeList.get(2)),
                new WeightedUndirectedEdge<>(33D, nodeList.get(2), nodeList.get(3)),
                new WeightedUndirectedEdge<>(10D, nodeList.get(3), nodeList.get(0)),

                new WeightedUndirectedEdge<>(13D, nodeList.get(0), nodeList.get(2)),
                new WeightedUndirectedEdge<>(4D, nodeList.get(2), nodeList.get(3)),
                new WeightedUndirectedEdge<>(76D, nodeList.get(3), nodeList.get(7)),
                new WeightedUndirectedEdge<>(17D, nodeList.get(7), nodeList.get(6))
        });
        directedEdgeList = Arrays.asList(new DirectedEdge[] {
                new DirectedEdge<>(21D, nodeList.get(0), nodeList.get(1)),
                new DirectedEdge<>(6D, nodeList.get(1), nodeList.get(2)),
                new DirectedEdge<>(33D, nodeList.get(2), nodeList.get(3)),
                new DirectedEdge<>(10D, nodeList.get(3), nodeList.get(0)),

                new DirectedEdge<>(13D, nodeList.get(0), nodeList.get(2)),
                new DirectedEdge<>(4D, nodeList.get(2), nodeList.get(3)),
                new DirectedEdge<>(76D, nodeList.get(3), nodeList.get(7)),
                new DirectedEdge<>(17D, nodeList.get(7), nodeList.get(6)),
                new DirectedEdge<>(17D, nodeList.get(3), nodeList.get(2))
        });
    }

    @Test
    public void undirectedGraphTest() {
        UndirectedGraph<Double, SimpleNode, WeightedUndirectedEdge<SimpleNode>> graphA = new WeightedUndirectedGraph<>();
        for (int i = 0; i < 4; i++) {
            graphA.addEdge((WeightedUndirectedEdge<SimpleNode>) undirectedEdgeList.get(i));
        }
        UndirectedGraph<Double, SimpleNode, WeightedUndirectedEdge<SimpleNode>> graphB = new WeightedUndirectedGraph();
        for (int i = 4; i < undirectedEdgeList.size(); i++) {
             graphB.addEdge((WeightedUndirectedEdge<SimpleNode>) undirectedEdgeList.get(i));

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
        UndirectedGraph<Double, SimpleNode, WeightedUndirectedEdge<SimpleNode>> graphA = new WeightedUndirectedGraph<>();
        for (int i = 0; i < 4; i++) {
            graphA.addEdge((WeightedUndirectedEdge<SimpleNode>) undirectedEdgeList.get(i));
        }
        UndirectedGraph<Double, SimpleNode, WeightedUndirectedEdge<SimpleNode>> graphB = new WeightedUndirectedGraph<>();
        for (int i = 4; i < undirectedEdgeList.size(); i++) {
             graphB.addEdge((WeightedUndirectedEdge<SimpleNode>) undirectedEdgeList.get(i));

        }
        List<SimpleNode> limitNodeList = Arrays.asList(
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
        SimpleDirectedGraph<Double, DirectedEdge<Double, SimpleNode>> graphA = new SimpleDirectedGraph<>(Double::sum);
        for (int i = 0; i < 4; i++) {
            graphA.addEdge(directedEdgeList.get(i));
        }
        SimpleDirectedGraph<Double, DirectedEdge<Double, SimpleNode>> graphB = new SimpleDirectedGraph<>(Double::sum);
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
        Community<SimpleNode> communityA = new Community<>(1L, nodeList.get(0));
        communityA.addNode(nodeList.get(1));
        Community<SimpleNode> communityB = new Community<>(2L, nodeList.get(2));
        communityB.addNode(nodeList.get(3));

        System.out.println(communityA);
        System.out.println(communityB);

        communityA.combineCommunity(communityB);
        System.out.println(communityA);
        System.out.println(communityB);
    }

    @Test
    public void communityTest2() {
        Community<SimpleNode> communityA = new Community<>(1L, nodeList.get(0));
        communityA.addNode(nodeList.get(1));
        Community<SimpleNode> communityB = new Community<>(2L, nodeList.get(2));
        communityB.addNode(nodeList.get(3));
        Community<SimpleNode> combineCommunity = Community.getCombineCommunity(3L, communityA, communityB);

        System.out.println(communityA);
        System.out.println(communityB);
        System.out.println(combineCommunity);
    }

    @Test
    public void edgeUtilsTest() {
        UndirectedGraph<Double, SimpleNode, WeightedUndirectedEdge<SimpleNode>> graphA = new WeightedUndirectedGraph<>();
        for (int i = 0; i < 4; i++) {
            graphA.addEdge((WeightedUndirectedEdge<SimpleNode>) undirectedEdgeList.get(i));
        }
        UndirectedGraph<Double, SimpleNode, WeightedUndirectedEdge<SimpleNode>> graphB = new WeightedUndirectedGraph<>();
        for (int i = 4; i < undirectedEdgeList.size(); i++) {
            graphB.addEdge((WeightedUndirectedEdge<SimpleNode>) undirectedEdgeList.get(i));

        }
        MyPrint.showSplitLine("*", 150);
        GraphUtils.showGraph(graphA);


        MyPrint.showSplitLine("*", 150);
        GraphUtils.showGraph(graphB);

        MyPrint.showSplitLine("*", 150);

        BinaryOperator<Double> binaryOperator = Double::sum;
        Double graphAEdgeValueSum = EdgeUtils.getEdgeValueSum(graphA.getEdgeSet(), binaryOperator);
        Double graphBEdgeValueSum = EdgeUtils.getEdgeValueSum(graphB.getEdgeSet(), binaryOperator);
        System.out.println(graphAEdgeValueSum);
        System.out.println(graphBEdgeValueSum);
    }
}

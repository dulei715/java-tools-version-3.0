import cn.edu.dll.io.print.MyPrint;
import cn.edu.dll.struct.graph.Node;
import cn.edu.dll.struct.graph.edge_impl.DirectedEdge;
import cn.edu.dll.struct.graph.edge_impl.UndirectedEdge;
import cn.edu.dll.struct.graph.graph_impl.SimpleDirectedGraph;
import cn.edu.dll.struct.graph.graph_impl.SimpleUndirectedGraph;
import cn.edu.dll.struct.graph.node_impl.SimpleNode;
import cn.edu.dll.struct.graph.utils.GraphTools;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GraphTest {

    public static List<Node> nodeList;

    public static List<UndirectedEdge> undirectedEdgeList;

    public static List<DirectedEdge> directedEdgeList;

    @Before
    public void before() {
        nodeList = Arrays.asList(new Node[] {
                new SimpleNode(1), new SimpleNode(2),
                new SimpleNode(3), new SimpleNode(4),
                new SimpleNode(5), new SimpleNode(6),
                new SimpleNode(7), new SimpleNode(8)
        });
        undirectedEdgeList = Arrays.asList(new UndirectedEdge[] {
                new UndirectedEdge(21D, nodeList.get(0), nodeList.get(1)),
                new UndirectedEdge(6D, nodeList.get(1), nodeList.get(2)),
                new UndirectedEdge(33D, nodeList.get(2), nodeList.get(3)),
                new UndirectedEdge(10D, nodeList.get(3), nodeList.get(0)),

                new UndirectedEdge(13D, nodeList.get(0), nodeList.get(2)),
                new UndirectedEdge(4D, nodeList.get(2), nodeList.get(3)),
                new UndirectedEdge(76D, nodeList.get(3), nodeList.get(7)),
                new UndirectedEdge(17D, nodeList.get(7), nodeList.get(6))
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
        SimpleUndirectedGraph graphA = new SimpleUndirectedGraph();
        for (int i = 0; i < 4; i++) {
            graphA.addEdge(undirectedEdgeList.get(i));
        }
        SimpleUndirectedGraph graphB = new SimpleUndirectedGraph();
        for (int i = 4; i < undirectedEdgeList.size(); i++) {
             graphB.addEdge(undirectedEdgeList.get(i));

        }
        MyPrint.showSplitLine("*", 150);
        GraphTools.showGraph(graphA);


        MyPrint.showSplitLine("*", 150);
        GraphTools.showGraph(graphB);

        MyPrint.showSplitLine("*", 150);
        graphA.combineGraph(graphB);
        GraphTools.showGraph(graphA);
    }

    @Test
    public void directedGraphTest() {
        SimpleDirectedGraph graphA = new SimpleDirectedGraph();
        for (int i = 0; i < 4; i++) {
            graphA.addEdge(directedEdgeList.get(i));
        }
        SimpleDirectedGraph graphB = new SimpleDirectedGraph();
        for (int i = 4; i < directedEdgeList.size(); i++) {
            graphB.addEdge(directedEdgeList.get(i));

        }
        MyPrint.showSplitLine("*", 150);
        GraphTools.showGraph(graphA);


        MyPrint.showSplitLine("*", 150);
        GraphTools.showGraph(graphB);

        MyPrint.showSplitLine("*", 150);
        graphA.combineGraph(graphB);
        GraphTools.showGraph(graphA);
    }
}

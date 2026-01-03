package cn.edu.dll.struct.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class HistoryGraphStruct {
    private Integer maxSaveLength;
    private LinkedList<Graph> historyGraphQueue;

    public HistoryGraphStruct(Integer maxSaveLength) {
        this.maxSaveLength = maxSaveLength;
        this.historyGraphQueue = new LinkedList<>();
    }

    public void addHistoryGraph(Graph historyGraph) {
        if (this.historyGraphQueue.size() >= this.maxSaveLength) {
            historyGraphQueue.poll();
        }
        this.historyGraphQueue.offer(historyGraph);
    }

    public void getInvertEdgeAverage(Integer invertSize) {
        if (invertSize > this.maxSaveLength) {
            throw new RuntimeException("The invert size is larger than the maxmum saving length of history data!");
        }
        Iterator<Graph> descendingIterator = this.historyGraphQueue.descendingIterator();
        Graph tempGraph;
        for (int count = 1; count <= invertSize && descendingIterator.hasNext(); count++) {
            tempGraph = descendingIterator.next();

        }
    }

}

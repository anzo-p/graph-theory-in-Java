package com.anzop;

import com.anzop.graph.Edge;
import com.anzop.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class TopologicalSort {

    private final DepthFirstSearch dfs;

    private final ArrayList<Edge> topOrder;

    private final HashSet<String> added;

    public TopologicalSort(Graph graph) {
        dfs = new DepthFirstSearch(graph);
        added = new HashSet<>();
        topOrder = new ArrayList<>();
    }

    public ArrayList<Edge> sort() {
        dfs.initialize();

        dfs.graph.getVerticesSorted().forEach(vertex -> {
            dfs.traverseWhileNext(vertex);

            Collections.reverse(dfs.path);
            dfs.path.forEach(edge -> {
                String vertexLabel = edge.getDestination().getLabel();
                if (!added.contains(vertexLabel)) {
                    topOrder.add(edge);
                    added.add(vertexLabel);
                }
            });
        });

        Collections.reverse(topOrder);

        return topOrder;
    }
}

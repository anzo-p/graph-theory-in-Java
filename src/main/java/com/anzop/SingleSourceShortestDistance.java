package com.anzop;

import com.anzop.graph.Edge;
import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingleSourceShortestDistance {

    private final Graph graph;

    private final ArrayList<Edge> topSorted;

    private final Map<Vertex, Integer> distances = new HashMap<>();

    public SingleSourceShortestDistance(Graph graph) {
        this.graph = graph;

        topSorted = new TopologicalSort(graph).sort();

        topSorted.forEach(edge -> distances.put(edge.getDestination(), Integer.MAX_VALUE));

        topSorted.stream().findFirst().ifPresent(edge -> distances.put(edge.getDestination(), 0));
    }

    public Map<Vertex, Integer> find() {
        for (Edge edge : topSorted)
            for (Edge destination : graph.getEdges(edge.getDestination())) {
                int currentOptimal = distances.get(destination.getDestination());
                int newDistance = distances.get(edge.getDestination()) + destination.getWeight();
                distances.put(destination.getDestination(), Math.min(currentOptimal, newDistance));
            }

        return distances;
    }
}

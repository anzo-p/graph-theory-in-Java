package com.anzop;

import com.anzop.graph.Edge;
import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Works with - Directional, Acyclic, Negative Weights

    O(V + E)
 */


public class SingleSourceShortestDistance {

    private final Graph graph;

    private final List<Edge> topSorted;

    private final Map<Vertex, Integer> distances = new HashMap<>();

    private final Map<Vertex, List<String>> paths = new HashMap<>();

    public SingleSourceShortestDistance(Graph graph) {
        this.graph = graph;

        topSorted = new TopologicalSort(graph).sort();

        for (Edge edge : topSorted) {
            distances.put(edge.getDestination(), Integer.MAX_VALUE);
            paths.put(edge.getDestination(), new ArrayList<>());
        }

        topSorted.stream().findFirst().ifPresent(edge -> {
            distances.put(edge.getDestination(), 0);
            paths.get(edge.getDestination()).add(edge.getDestination().getLabel());
        });
    }

    public Map<Vertex, Integer> find() {
        for (Edge from : topSorted)
            for (Edge to : graph.getEdges(from.getDestination())) {
                int bestKnown = distances.get(to.getDestination());
                int newDistance = distances.get(from.getDestination()) + to.getWeight();

                if (newDistance < bestKnown) {
                    distances.put(to.getDestination(), newDistance);

                    List<String> shortestPath = new ArrayList<>(paths.get(from.getDestination()));
                    shortestPath.add(to.getDestination().getLabel());
                    paths.put(to.getDestination(), shortestPath);
                }
            }
        return distances;
    }

    public Map<Vertex, List<String>> getPaths() {
        return paths;
    }
}

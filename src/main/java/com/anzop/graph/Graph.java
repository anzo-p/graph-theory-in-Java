package com.anzop.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private final Map<Vertex, ArrayList<Edge>> graph = new HashMap<>();

    public void addVertex(Vertex source) {
        graph.put(source, new ArrayList<>());
    }

    public void addVertex(String label) {
        addVertex(new Vertex(label));
    }

    public void removeVertex(Vertex deleteVertex) {
        Map<Vertex, ArrayList<Edge>> deletedEdges = new HashMap<>();

        /*
            Successfully pops a vertex and its edges even from a complete bidirectional graph.
            The edges do not know of their source vertices so will have to scan full graph.
            Its alright as this is only a practice dummy for study on graph algorithms.
        */
        graph.forEach((vertex, edges) ->
            edges.forEach(edge -> {
                if (edge.getDestination().equals(deleteVertex)) {
                    if (deletedEdges.containsKey(vertex)) {
                        deletedEdges.get(vertex).add(edge);
                    } else {
                        deletedEdges.put(vertex, new ArrayList<>(Collections.singletonList(edge)));
                    }
                    deletedEdges.get(vertex).add(edge);
                }
            })
        );

        deletedEdges.forEach((vertex, edges) -> edges.forEach(edge -> graph.get(vertex).remove(edge)));
        graph.remove(deleteVertex);
    }

    public void removeVertex(String label) {
        removeVertex(new Vertex(label));
    }

    public void addEdge(Vertex source, Edge edge) {
        if (!graph.containsKey(source)) addVertex(source);

        Vertex destination = edge.getDestination();
        if (!graph.containsKey(destination)) addVertex(destination);

        graph.get(source).add(edge);
    }

    public void addEdge(Vertex source, Vertex destination, int weight) {
        addEdge(source, new Edge(destination, weight));
    }

    public void addEdge(String source, String destination, int weight) {
        addEdge(new Vertex(source), new Edge(new Vertex(destination), weight));
    }

    public void removeEdge(Vertex vertex, Edge edge) {
        graph.get(vertex).remove(edge);
    }

    public List<Vertex> getKeysSorted() {
        return graph
                .keySet()
                .stream()
                .sorted(Comparator.comparing(Vertex::getLabel))
                .collect(Collectors.toList());
    }

    public List<Edge> getEdgesSorted(Vertex vertex) {
        return graph
                .get(vertex)
                .stream()
                .sorted(Comparator.comparing(edge -> edge.getDestination().getLabel()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Vertex vertex : getKeysSorted()) {
            result
                    .append(vertex.getLabel())
                    .append(" -> ")
                    .append(getEdgesSorted(vertex).toString())
                    .append("\n");
        }

        return result.toString().replaceAll("[\\[\\]]", "");
    }
}

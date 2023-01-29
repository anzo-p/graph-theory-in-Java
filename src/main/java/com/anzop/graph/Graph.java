package com.anzop.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private final Map<Vertex, List<Edge>> graph = new HashMap<>();

    private final Map<String, Vertex> vertices = new HashMap<>();

    public void addVertex(Vertex vertex) {
        if (!vertices.containsKey(vertex.getLabel())) {
            vertices.put(vertex.getLabel(), vertex);
            graph.put(vertex, new ArrayList<>());
        }
    }

    public void addVertex(String label) {
        Vertex vertex = vertices.getOrDefault(label, new Vertex(label));
        addVertex(vertex);
    }

    public void removeVertex(Vertex deleteVertex) {
        Map<Vertex, List<Edge>> deletedEdges = new HashMap<>();

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
                }
            })
        );

        deletedEdges.forEach((vertex, edges) -> edges.forEach(edge -> graph.get(vertex).remove(edge)));
        graph.remove(deleteVertex);
    }

    public void removeVertex(String label) {
        removeVertex(vertices.get(label));
        vertices.remove(label);
    }

    public void addEdge(Vertex vertex, Edge edge) {
        addVertex(vertex);
        addVertex(edge.getDestination());

        List<Edge> edges = graph.get(vertex);

        if (!edges.contains(edge)) {
            edges.add(edge);
        }
    }

    public void addEdge(Vertex from, Vertex to, int weight) {
        Optional<Edge> edge = graph
                .getOrDefault(from, new ArrayList<>())
                .stream()
                .filter(e -> e.getDestination().equals(to) && e.getWeight() == weight)
                .findFirst();

        addEdge(from, edge.orElse(new Edge(to, weight)));
    }

    public void addEdge(String from, String to, int weight) {
        Vertex a = vertices.getOrDefault(from, new Vertex(from));
        Vertex b = vertices.getOrDefault(to, new Vertex(to));
        addEdge(a, b, weight);
    }

    public void addEdge(String from, String to) {
        addEdge(from, to, 0);
    }

    public void addBidirectionalEdge(Vertex from, Vertex to, int weight) {
        addEdge(from, to, weight);
        addEdge(to, from, weight);
    }

    public void addBidirectionalEdge(String from, String to, int weight) {
        addEdge(from, to, weight);
        addEdge(to, from, weight);
    }

    public void addBidirectionalEdge(String from, String to) {
        addBidirectionalEdge(from, to, 0);
    }

    public void removeEdge(Vertex vertex, Edge edge) {
        graph.get(vertex).remove(edge);
    }

    public List<Edge> getVertex(Vertex vertex) {
        return graph.get(vertex);
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(graph.keySet());
    }

    public List<Vertex> getVerticesSorted() {
        return graph
                .keySet()
                .stream()
                .sorted(Comparator.comparing(Vertex::getLabel))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Edge> getEdges(Vertex vertex) {
        return graph.get(vertex);
    }

    public List<Edge> getEdgesSorted(Vertex vertex) {
        return graph
                .getOrDefault(vertex, new ArrayList<>())
                .stream()
                .sorted(Comparator
                                .comparing(Edge::getDestination, Comparator.comparing(Vertex::getLabel))
                                .thenComparing(Edge::getWeight))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int getSize() {
        return getVertices().size();
    }

    public Graph invertedWeights() {
        Graph inverted = new Graph();

        graph.forEach((vertex, edges) ->
                edges.forEach(edge -> inverted.addEdge(vertex, edge.getDestination(), edge.getWeight() * -1))
        );

        return inverted;
    }

    public Graph invertedDirections() {
        Graph inverted = new Graph();

        graph.forEach((vertex, edges) ->
                edges.forEach(edge -> inverted.addEdge(edge.getDestination(), vertex, edge.getWeight()))
        );

        return inverted;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Vertex vertex : getVerticesSorted()) {
            result
                    .append(vertex)
                    .append(" -> ")
                    .append(getEdgesSorted(vertex).toString())
                    .append("\n");
        }

        return result.toString().replaceAll("[\\[\\]]", "");
    }
}

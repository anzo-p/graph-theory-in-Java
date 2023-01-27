package com.anzop;

import com.anzop.graph.Edge;
import com.anzop.graph.Graph;
import com.anzop.graph.SearchResult;
import com.anzop.graph.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class BaseSearch {

    protected final Graph graph;

    protected HashSet<Vertex> visited = new HashSet<>();

    protected List<Edge> path;

    protected void initialize() {
        path = new ArrayList<>();
        visited = new HashSet<>();
    }

    protected Edge makeStarterEdge(Vertex vertex) {
        return new Edge(vertex, 0);
    }

    protected void bookkeeping(Edge edge) {
        path.add(edge);
        visited.add(edge.getDestination());
    }

    protected SearchResult makeResponse(List<Edge> path) {
        return new SearchResult(
                path
                        .stream()
                        .map(Edge::getDestination)
                        .map(Vertex::getLabel)
                        .collect(Collectors.joining(", ")),
                path
                        .stream()
                        .map(Edge::getWeight)
                        .reduce(0, Integer::sum)
        );
    }

    public BaseSearch(Graph graph) {
        this.graph = graph;
    }
}
package com.anzop;

import com.anzop.graph.*;

import java.util.List;
import java.util.stream.Collectors;

/*
    Depth First Search, DFS is a Base traversal algorithm

    time complexity O(V + E) - which is linear to the size of graph
 */


public class DepthFirstSearch extends BaseSearch {

    private int groupCode = 0;

    protected void initialize() {
        super.initialize();

        groupCode = 0;
    }

    protected void bookkeeping(Edge edge) {
        super.bookkeeping(edge);

        Vertex vertex = edge.getDestination();
        vertex.setGroupCode(groupCode);
    }

    private void traverse(Edge from) {
        bookkeeping(from);

        graph.getEdges(from.getDestination()).forEach(edge -> {
            if (!visited.contains(edge.getDestination())) {
                traverse(edge);
            }
        });
    }

    private void traverse(Edge from, Vertex to) {
        Vertex vertex = from.getDestination();
        bookkeeping(from);

        if (vertex.equals(to)) {
            return;
        }

        graph.getEdges(vertex).forEach(edge -> {
            if (!visited.contains(edge.getDestination())) {
                traverse(edge, to);
            }
        });
    }

    public DepthFirstSearch(Graph graph) {
        super(graph);
    }

    public SearchResult traverseWhileNext(Vertex from) {
        initialize();

        if (graph.getVertices().contains(from)) {
            traverse(makeStarterEdge(from));
        }
        return makeResponse(path);
    }

    public SearchResult traverseInto(Vertex from, Vertex to) {
        initialize();

        if (graph.getVertices().contains(from)) {
            traverse(makeStarterEdge(from), to);
        }
        return makeResponse(path);
    }

    public SearchResult fullTraverse() {
        initialize();

        graph.getVerticesSorted().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                traverse(makeStarterEdge(vertex));
                groupCode++;
            }
        });

        return makeResponse(path);
    }

    public List<Integer> getComponents() {
        return graph
                .getVertices()
                .stream()
                .map(Vertex::getGroupCode)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}

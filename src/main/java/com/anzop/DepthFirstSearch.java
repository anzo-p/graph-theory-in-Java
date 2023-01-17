package com.anzop;

import com.anzop.graph.*;

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

        graph.getEdgesSorted(from.getDestination()).forEach(edge -> {
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

        graph.getEdgesSorted(vertex).forEach(edge -> {
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

        if (graph.getKeysSorted().contains(from)) {
            traverse(makeStarterEdge(from));
        }
        return makeResponse(path);
    }

    public SearchResult traverseInto(Vertex from, Vertex to) {
        initialize();

        if (graph.getKeysSorted().contains(from)) {
            traverse(makeStarterEdge(from), to);
        }
        return makeResponse(path);
    }

    public SearchResult fullTraverse() {
        initialize();

        graph.getKeysSorted().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                traverse(makeStarterEdge(vertex));
            }
        });

        return makeResponse(path);
    }

    public int findComponents() {
        initialize();

        graph.getKeysSorted().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                groupCode++;
                traverse(makeStarterEdge(vertex));
            }
        });
        
        return groupCode;
    }
}

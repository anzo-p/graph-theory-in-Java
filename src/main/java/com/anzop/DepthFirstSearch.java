package com.anzop;

/*
    Depth First Search, DFS is a Base traversal algorithm

    time complexity O(V + E) - which is linear to the size of graph
 */

import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;

import java.util.ArrayList;
import java.util.HashSet;

public class DepthFirstSearch {
    private final Graph graph;

    private HashSet<Vertex> visited = new HashSet<>();

    private ArrayList<String> path;

    private int groupCode = 0;

    private void initialize() {
        visited = new HashSet<>();
        path = new ArrayList<>();
        groupCode = 0;
    }

    private void bookkeeping(Vertex vertex) {
        vertex.setGroupCode(groupCode);
        path.add(vertex.getLabel());
        visited.add(vertex);
    }

    private void traverse(Vertex from) {
        bookkeeping(from);

        graph.getEdgesSorted(from).forEach(edge -> {
            if (!visited.contains(edge.getDestination())) {
                traverse(edge.getDestination());
            }
        });
    }

    private void traverse(Vertex from, Vertex to) {
        bookkeeping(from);

        if (from.equals(to)) {
            return;
        }

        graph.getEdgesSorted(from).forEach(edge -> {
            if (!visited.contains(edge.getDestination())) {
                traverse(edge.getDestination(), to);
            }
        });
    }

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public ArrayList<String> traverseWhileNext(Vertex from) {
        initialize();

        if (graph.getKeysSorted().contains(from)) {
            traverse(from);
        }
        return path;
    }

    public ArrayList<String> traverseInto(Vertex from, Vertex to) {
        initialize();

        if (graph.getKeysSorted().contains(from)) {
            traverse(from, to);
        }
        return path;
    }

    public ArrayList<String> fullTraverse() {
        initialize();

        graph.getKeysSorted().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                traverse(vertex);
            }
        });

        return path;
    }

    public int findComponents() {
        initialize();

        graph.getKeysSorted().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                groupCode++;
                traverse(vertex);
            }
        });
        
        return groupCode;
    }

}

package com.anzop;

/*
    Breadth First Search, BFS is a Base traversal algorithm

    time complexity O(V + E) - which is linear to the size of graph

    Most importantly a BFS optimizes on two things
    - introduces assessment on which branch might be more optimal to take next
    - the memory footprint of traversal
 */

import com.anzop.graph.Edge;
import com.anzop.graph.Graph;
import com.anzop.graph.Response;
import com.anzop.graph.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class BreadthFirstSearch {
    private final Graph graph;

    private HashSet<Vertex> visited = new HashSet<>();

    private ArrayList<Edge> path;

    private final PriorityQueue<Edge> queue = new PriorityQueue<>();

    private void initialize() {
        visited = new HashSet<>();
        path = new ArrayList<>();
    }

    private void bookkeeping(Edge edge) {
        path.add(edge);
        visited.add(edge.getDestination());
    }

    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public Response makeResponse(ArrayList<Edge> path) {
        return new Response(
                path.stream().map(Edge::getDestination).map(Vertex::getLabel).collect(Collectors.joining(", ")),
                path.stream().map(Edge::getWeight).reduce(0, Integer::sum)
        );
    }

    public Response traverseDijkstra(Vertex from , Vertex to) {
        initialize();

        queue.add(new Edge(from, 0));

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            Vertex vertex = edge.getDestination();

            bookkeeping(edge);

            if (vertex.equals(to)) return makeResponse(path);

            graph.getEdgesSorted(vertex).forEach(nextEdge -> {
                if (!visited.contains(nextEdge.getDestination())) {
                    queue.add(nextEdge);
                }
            });
        }

        return makeResponse(path);
    }
}

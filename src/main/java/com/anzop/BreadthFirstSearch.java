package com.anzop;

import com.anzop.graph.*;

import java.util.PriorityQueue;

/*
    Breadth First Search, BFS is a Base traversal algorithm

    time complexity O(V + E) - which is linear to the size of graph

    Most importantly a BFS optimizes on two things
    - introduces assessment on which branch might be more optimal to take next
    - the memory footprint of traversal
 */

public class BreadthFirstSearch extends BaseSearch {

    private final PriorityQueue<Edge> queue = new PriorityQueue<>();

    public BreadthFirstSearch(Graph graph) {
        super(graph);
    }

    public SearchResult traverseDijkstra(Vertex from , Vertex to) {
        initialize();

        queue.add(makeStarterEdge(from));

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

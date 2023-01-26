package com.anzop;

import com.anzop.graph.*;

import java.util.PriorityQueue;

/*
    Breadth First Search, BFS

    - Works with any graph, simple and practical when the graph has unweighted edges

    - Base traversal algorithm, better than Depth First search in that it
      - introduces assessment on which branch might be more optimal to take next
      - the memory footprint of traversal

    - O(V + E) - which is linear to the size of graph
 */

public class BreadthFirstSearch extends BaseSearch {

    private final PriorityQueue<Edge> queue = new PriorityQueue<>();

    public BreadthFirstSearch(Graph graph) {
        super(graph);
    }

    public SearchResult traverse(Vertex from , Vertex to) {
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

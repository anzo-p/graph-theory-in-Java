package com.anzop;

import com.anzop.graph.Edge;
import com.anzop.graph.Graph;
import com.anzop.graph.SearchResult;
import com.anzop.graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
    Dijkstra

    Works with Bidirectional, Cyclic, Only Positive Weights,

    Best when the graph has weighted edges

    O((V + E) * Log V) if prio queue was indexed

    A* and the likes are beefed up Dijksta's
 */

public class Dijkstra extends BaseSearch{

    private final PriorityQueue<Edge> queue = new PriorityQueue<>();

    private final HashMap<Vertex, Integer> distances = new HashMap<>();

    private ArrayList<Edge> runDijkstra(Vertex from , Vertex to) {
        queue.add(makeStarterEdge(from));
        distances.put(from, 0);

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            Vertex vertex = edge.getDestination();

            bookkeeping(edge);

            if (vertex.equals(to))
                return path;

            for (Edge nextEdge : graph.getEdgesSorted(vertex)) {

                int leastKnownDistance = distances.getOrDefault(nextEdge.getDestination(), Integer.MAX_VALUE);
                int newDistance = distances.getOrDefault(edge.getDestination(), Integer.MAX_VALUE) + nextEdge.getWeight();

                if (!visited.contains(nextEdge.getDestination()) || (newDistance < leastKnownDistance)) {
                    queue.add(nextEdge);
                    distances.put(nextEdge.getDestination(), newDistance);
                }
            }
        }

        return path;
    }

    public Dijkstra(Graph graph) {
        super(graph);
    }

    public SearchResult dijkstra(Vertex from, Vertex to) {
        initialize();

        ArrayList<Edge> searchRun = runDijkstra(from, to);

        if (searchRun.isEmpty())
            return makeResponse(searchRun);

        Collections.reverse(searchRun);
        ArrayList<Edge> cleanupRun = new ArrayList<>();

        Edge curr = searchRun.get(0);
        cleanupRun.add(curr);

        for (Edge next : searchRun.subList(1, searchRun.size())) {
            if (graph.getVertex(curr.getDestination()).stream().anyMatch(edge ->
                    edge.getDestination().equals(next.getDestination()))) {

                cleanupRun.add(next);
                curr = next;
            }
        }

        Collections.reverse(cleanupRun);

        return makeResponse(cleanupRun);
    }
}

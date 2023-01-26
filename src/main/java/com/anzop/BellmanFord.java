package com.anzop;

/*
    Bellman Ford

    - Works with Bidirectional, Cyclic, Positive or Negative weights

    - Can, and is good to, detect negative cycles

    - O(VE), notice that 'for every parent for every child' still makes only one pass
 */

import com.anzop.graph.Edge;
import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;

import java.util.HashMap;

public class BellmanFord extends BaseSearch {

    private final HashMap<Vertex, Double> distances = new HashMap<>();

    BellmanFord(Graph graph) {
        super(graph);
    }

    private void compute(Boolean detectNegativeCycle) {

        // one less than V count
        for (int i = 0; i < graph.getSize() - 1; i++) {
            for (Vertex vertex : graph.getVertices()) {
                for (Edge edge : graph.getEdges(vertex)) {

                    Double currentDistance = distances.get(edge.getDestination());
                    Double newDistance = distances.get(vertex) + edge.getWeight();

                    if (newDistance < currentDistance) {
                        if (detectNegativeCycle) {
                            // INF bubbles over as arithmetic over INF results in INF and doesnt overflow
                            distances.put(edge.getDestination(), Double.NEGATIVE_INFINITY);
                        } else {
                            distances.put(edge.getDestination(), newDistance);
                        }
                    }
                }
            }
        }
    }

    public HashMap<Vertex, Double> solve() {
        initialize();

        for (Vertex vertex : graph.getVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }

        distances.put(graph.getVerticesSorted().get(0), 0.0);

        compute(false);
        compute(true);

        return distances;
    }
}

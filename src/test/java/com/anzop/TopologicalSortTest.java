package com.anzop;

import com.anzop.graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopologicalSortTest {

    @Test
    void testTopologicalSorting() {
        Graph g = new Graph();

        // see https://youtu.be/09_LlHjoEiY?t=3938
        g.addEdge("A", "D");

        g.addEdge("B", "D");

        g.addEdge("C", "A");
        g.addEdge("C", "B");

        g.addEdge("D", "G");
        g.addEdge("D", "H");

        g.addEdge("E", "A");
        g.addEdge("E", "D");
        g.addEdge("E", "F");

        g.addEdge("F", "J");
        g.addEdge("F", "K");

        g.addEdge("G", "I");

        g.addEdge("H", "I");
        g.addEdge("H", "J");

        g.addEdge("I", "L");

        g.addEdge("J", "L");
        g.addEdge("J", "M");

        g.addEdge("K", "J");

        List<String> result = new TopologicalSort(g)
                .sort()
                .stream()
                .map(e -> e.getDestination().getLabel())
                .collect(Collectors.toCollection(ArrayList::new));

        // there would be many answers as topological orders are not unique
        // this answer is expected because the keys and value lists in the graph are sorted
        assertEquals("[E, F, K, C, B, A, D, G, I, L, H, J, M]", result.toString());
    }
}

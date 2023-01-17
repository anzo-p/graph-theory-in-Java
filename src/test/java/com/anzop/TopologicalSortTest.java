package com.anzop;

import com.anzop.graph.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

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

        ArrayList<String> result = new TopologicalSort(g).sort();

        // there would be many answers as topological orders are not unique
        // this answer is expected because of orderings in the graph queries
        assertEquals("[E, F, K, C, B, A, D, G, I, L, H, J, M]", result.toString());
    }
}

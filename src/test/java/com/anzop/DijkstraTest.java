package com.anzop;

import com.anzop.graph.ComplexExample;
import com.anzop.graph.SearchResult;
import com.anzop.graph.Vertex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraTest {

    @Test
    void TestTraverseIntoComplexPath() {
        Dijkstra bfs = new Dijkstra(new ComplexExample().get());

        /*
            These Vertices are parallel to the optimal path such that Dijkstra will find them too.
            A cleanup run is necessary to only select the actual nodes that were traversed through.
         */
        bfs.graph.addEdge("B", "Y", 2);
        bfs.graph.addEdge("Y", "X", 1);
        bfs.graph.addEdge("X", "Z", 1);
        bfs.graph.addEdge("Z", "E", 5);

        String expectedPath = "S, B, H, G, E";
        int expectedCost = 7;

        SearchResult res = bfs.dijkstra(new Vertex("S"), new Vertex("E"));

        assertEquals(expectedPath, res.getPath());
        assertEquals(expectedCost, res.getCost());
    }
}

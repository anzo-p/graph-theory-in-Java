package com.anzop;

import com.anzop.graph.ComplexExample;
import com.anzop.graph.SearchResult;
import com.anzop.graph.Vertex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreadthFirstSearchTest {

    @Test
    void TestTraverseIntoComplexPath() {
        BreadthFirstSearch bfs = new BreadthFirstSearch(new ComplexExample().get());

        String expectedPath = "S, B, H, G, E";
        int expectedCost = 7;

        SearchResult res = bfs.traverseDijkstra(new Vertex("S"), new Vertex("E"));

        assertEquals(expectedPath, res.getPath());
        assertEquals(expectedCost, res.getCost());
    }
}

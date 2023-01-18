package com.anzop;

import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

public class SingleSourceShortestDistanceTest {

    private final Graph graph;

    public SingleSourceShortestDistanceTest() {
        Graph g = new Graph();

        // see https://youtu.be/09_LlHjoEiY?t=4571
        g.addEdge("A", "B", 3);
        g.addEdge("A", "C", 6);

        g.addEdge("B", "C", 4);
        g.addEdge("B", "D", 4);
        g.addEdge("B", "E", 11);

        g.addEdge("C", "D", 8);
        g.addEdge("C", "G", 11);

        g.addEdge("D", "E", -4);
        g.addEdge("D", "F", 5);
        g.addEdge("D", "G", 2);

        g.addEdge("E", "H", 9);

        g.addEdge("F", "H", 1);

        g.addEdge("G", "H", 2);

        this.graph = g;
    }

    @Test
    void testSingleSourceShortestDistance() {

        SingleSourceShortestDistance sssd = new SingleSourceShortestDistance(graph);

        Map<Vertex, Integer> shortestPath = sssd.find();

        System.out.println(shortestPath);
    }

    @Test
    void testSingleSourceLongestLongestDistance() {

        Map<Vertex, Integer> invertedLongestPaths = new SingleSourceShortestDistance(graph.invertedWeights()).find();

        Map<Vertex, Integer> longestPaths = invertedLongestPaths.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * -1));

        System.out.println(longestPaths);
    }
}

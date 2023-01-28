package com.anzop;

import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        String distancesFormatted = Arrays.toString(
                sssd
                        .find()
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparing(e -> e.getKey().getLabel()))
                        .map(e -> e.getKey().getLabel() + "=" + e.getValue())
                        .toArray()
        );

        assertEquals(
                "[A=0, B=3, C=6, D=7, E=3, F=12, G=9, H=11]",
                distancesFormatted
        );

        String pathsFormatted = Arrays.toString(
                sssd
                        .getPaths()
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparing(e -> e.getKey().getLabel()))
                        .map(e -> e.getKey().getLabel() + "=" + e.getValue())
                        .toArray()
        );

        assertEquals(
                "[A=[A], B=[A, B], C=[A, C], D=[A, B, D], E=[A, B, D, E], F=[A, B, D, F], G=[A, B, D, G], H=[A, B, D, G, H]]",
                pathsFormatted
        );
    }

    @Test
    void testSingleSourceLongestLongestDistance() {

        SingleSourceShortestDistance sssd = new SingleSourceShortestDistance(graph.invertedWeights());

        Map<Vertex, Integer> longestPaths = sssd
                .find()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * -1));

        String distancesFormatted = Arrays.toString(
                longestPaths
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparing(e -> e.getKey().getLabel()))
                        .map(e -> e.getKey().getLabel() + "=" + e.getValue())
                        .toArray()
        );

        assertEquals(
                "[A=0, B=3, C=7, D=15, E=14, F=20, G=18, H=23]",
                distancesFormatted
        );

        String pathsFormatted = Arrays.toString(
                sssd
                        .getPaths()
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparing(e -> e.getKey().getLabel()))
                        .map(e -> e.getKey().getLabel() + "=" + e.getValue())
                        .toArray()
        );

        assertEquals(
                "[A=[A], B=[A, B], C=[A, B, C], D=[A, B, C, D], E=[A, B, E], F=[A, B, C, D, F], G=[A, B, C, G], H=[A, B, E, H]]",
                pathsFormatted
        );
    }
}

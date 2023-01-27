package com.anzop;

import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BellmanFordTest {

    // examples from the video https://youtu.be/09_LlHjoEiY?t=6884

    @Test
    void TestBellmanFord1() {
        Graph g = new Graph();

        g.addEdge("0", "1", 1);
        g.addEdge("0", "2", 1);
        g.addEdge("2", "1", 1);
        g.addEdge("1", "3", 4);
        g.addEdge("3", "2", -6);
        g.addEdge("3", "4", 1);
        g.addEdge("3", "5", 1);
        g.addEdge("4", "5", 1);
        g.addEdge("4", "6", 1);
        g.addEdge("5", "6", 1);

        BellmanFord bf = new BellmanFord(g);

        Map<Vertex, Double> result = bf.solve();

        List<Double> expected = new ArrayList<>(Arrays.asList(
                0.0,
                Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY
        ));

        assertEquals(expected, result.values().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }

    @Test
    void TestBellmanFord2() {
        Graph g = new Graph();
        g.addEdge("0", "1",   5);
        g.addEdge("1", "2",  20);
        g.addEdge("1", "5",  30);
        g.addEdge("1", "6",  60);
        g.addEdge("2", "3",  10);
        g.addEdge("2", "4",  75);
        g.addEdge("3", "2", -15);
        g.addEdge("4", "9", 100);
        g.addEdge("5", "6",   5);
        g.addEdge("5", "8",  50);
        g.addEdge("6", "7", -50);
        g.addEdge("7", "8", -10);

        BellmanFord bf = new BellmanFord(g);

        Map<Vertex, Double> result = bf.solve();

        List<Double> expected = new ArrayList<>(Arrays.asList(
                0.0, 5.0,
                Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                35.0, 40., -10.0, -20.0,
                Double.NEGATIVE_INFINITY
        ));

        assertEquals(expected, result.values().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }

    @Test
    void TestBellmanFord3() {
        Graph g = new Graph();
        g.addEdge("0", "1", 1);
        g.addEdge("1", "2", 1);
        g.addEdge("2", "4", 1);
        g.addEdge("4", "3", -3);
        g.addEdge("3", "2", 1);
        g.addEdge("1", "5", 4);
        g.addEdge("1", "6", 4);
        g.addEdge("5", "6", 5);
        g.addEdge("6", "7", 4);
        g.addEdge("5", "7", 3);

        BellmanFord bf = new BellmanFord(g);

        Map<Vertex, Double> result = bf.solve();

        List<Double> expected = new ArrayList<>(Arrays.asList(
                0.0, 1.0,
                Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                5.0, 5.0, 8.0
        ));

        assertEquals(expected, result.values().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }
}

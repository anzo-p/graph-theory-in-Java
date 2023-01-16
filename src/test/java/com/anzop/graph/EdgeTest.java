package com.anzop.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeTest {

    @Test
    void TestConstructorCreatesAndReturnsEdgeWithGivenArguments() {
        Vertex v = new Vertex("A");
        Edge e = new Edge(v, 1);

        assertEquals(e.getDestination(), v);
        assertEquals(e.getWeight(), 1);
    }

    @Test
    void TestSetWeightSetsWeight() {
        Edge e = new Edge(new Vertex("A"), 1);

        assertEquals(e.getWeight(), 1);
        e.setWeight(2);
        assertEquals(e.getWeight(), 2);
    }

    @Test
    void TestToStringReturnsStringRepresentationOfEdge() {
        Edge e = new Edge(new Vertex("A"), 1);

        assertEquals(e.toString(), "(A, 1)");
    }
}

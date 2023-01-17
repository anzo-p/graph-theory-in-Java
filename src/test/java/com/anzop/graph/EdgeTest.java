package com.anzop.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EdgeTest {

    @Test
    void TestConstructorCreatesAndReturnsEdgeWithGivenArguments() {
        Vertex v = new Vertex("A");
        Edge e = new Edge(v, 1);

        assertEquals(e.getDestination(), v);
        assertEquals(e.getWeight(), 1);
    }

    @Test
    void TestToStringReturnsStringRepresentationOfEdge() {
        Edge e = new Edge(new Vertex("A"), 1);

        assertEquals(e.toString(), "(A, 1)");
    }

    @Test
    void TestHashEqualityBetweenDifferentObjectWithSameFields() {
        Edge e1 = new Edge(new Vertex("A"), 1);
        Edge e2 = new Edge(new Vertex("A"), 1);
        Edge e3 = new Edge(new Vertex("A"), 2);
        Edge e4 = new Edge(new Vertex("B"), 3);

        assertEquals(e1.hashCode(), e2.hashCode());
        assertEquals(e1, e2);

        assertNotEquals(e1.hashCode(), e3.hashCode());
        assertNotEquals(e1, e3);

        assertNotEquals(e1.hashCode(), e4.hashCode());
        assertNotEquals(e1, e4);
    }
}

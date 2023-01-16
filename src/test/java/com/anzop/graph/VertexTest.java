package com.anzop.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VertexTest {
    @Test
    void TestConstructorCreatesAndReturnsVertexWithGivenArguments() {
        Vertex v = new Vertex("A");
        assertEquals(v.getLabel(), "A");
    }

    @Test
    void TestHashEqualityBetweenDifferentObjectWithSameFields() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("A");

        assertEquals(v1.hashCode(), v2.hashCode());
        assertEquals(v1, v2);
    }
}


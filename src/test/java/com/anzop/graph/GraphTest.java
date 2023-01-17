package com.anzop.graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GraphTest {

    @Test
    void TestAddVertexAddsVertex() {
        Graph g = new Graph();
        g.addVertex(new Vertex("A"));

        String expected = "A -> \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestAddDuplicateVertexAddsVertexOnlyOnce() {
        Graph g = new Graph();
        g.addVertex(new Vertex("A"));
        g.addVertex(new Vertex("A"));

        String expected = "A -> \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestAddVertexDoesNotOverwriteExistingVertex() {
        Graph g = new Graph();
        g.addEdge("A", "A", 1);
        g.addEdge("A", "B", 1);
        g.addEdge("A", "C", 1);

        String expectedBefore =
                "A -> (A, 1), (B, 1), (C, 1)\n" +
                "B -> \n" +
                "C -> \n";

        assertEquals(expectedBefore, g.toString());

        g.addVertex("A");

        String notExpectedAfter =
                "A -> \n" +
                "B -> \n" +
                "C -> \n";

        String expectedAfter =
                "A -> (A, 1), (B, 1), (C, 1)\n" +
                "B -> \n" +
                "C -> \n";

        assertNotEquals(notExpectedAfter, g.toString());
        assertEquals(expectedAfter, g.toString());
    }

    @Test
    void TestAddVertexAsValueAddsVertex() {
        Graph g = new Graph();
        g.addVertex("A");

        String expected = "A -> \n";

        assertEquals(expected, g.toString());

    }

    @Test
    void TestRemoveVertexRemovesSoleVertexAndLeavesEmptyGraph() {
        Graph g = new Graph();
        Vertex v = new Vertex("A");
        g.addVertex(v);
        g.removeVertex(v);

        String expected = "";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestRemoveVertexRemovesVertexButLeavesOtherVertices() {
        Graph g = new Graph();
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        g.addVertex(v1);
        g.addVertex(v2);
        g.removeVertex(v1);

        String expected = "B -> \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestAddVertexAsValueAndRemoveAsVertexRemovesVertex() {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.removeVertex(new Vertex("A"));

        String expected = "B -> \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestAddAndRemoveVertexAsValuesRemovesVertex() {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.removeVertex("A");

        String expected = "B -> \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestAddEdgeAddsEdge() {
        Graph g = new Graph();
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        g.addVertex(v1);
        g.addVertex(v2);
        g.addEdge(v1, new Edge(v2, 1));

        String expected =
                "A -> (B, 1)\n" +
                "B -> \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestAddEdgesAsTupleOfVerticesAddsEdges() {
        Graph g = new Graph();
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        g.addEdge(v1, v2, 1);

        String expected =
                "A -> (B, 1)\n" +
                "B -> \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestAddEdgesAsTupleOfValuesAddsEdge() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);

        String expected =
                "A -> (B, 1)\n" +
                "B -> \n";

        assertEquals(expected, g.toString()); }

    @Test
    void TestAddUniDirectedBinaryCycleSuccess() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "A", 2);

        String expected =
                "A -> (B, 1)\n" +
                "B -> (A, 2)\n";

        assertEquals(expected, g.toString()); }

    @Test
    void TestAddTertiaryCycleSuccess() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 2);
        g.addEdge("C", "A", 3);

        String expected =
                "A -> (B, 1)\n" +
                "B -> (C, 2)\n" +
                "C -> (A, 3)\n";

        assertEquals(expected, g.toString()); }

    @Test
    void TestAddUniDirectedTertiaryCycleSuccess() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 2);
        g.addEdge("C", "A", 3);

        g.addEdge("A", "C", 4);
        g.addEdge("C", "B", 5);
        g.addEdge("B", "A", 6);

        String expected =
                "A -> (B, 1), (C, 4)\n" +
                "B -> (A, 6), (C, 2)\n" +
                "C -> (A, 3), (B, 5)\n";

        assertEquals(expected, g.toString()); }

    @Test
    void TestAddDuplicateEdgeAddsOnlyOneEdge() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("A", "B", 11);
        g.addEdge("A", "B", 1);

        String expected =
                "A -> (B, 1), (B, 11)\n" +
                "B -> \n";

        assertEquals(expected, g.toString()); }

    @Test
    void TestAddEdgeWithDuplicateDestinationButDifferentWeightSuccess() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("A", "B", 2);

        String expected =
                "A -> (B, 1), (B, 2)\n" +
                "B -> \n";

        assertEquals(expected, g.toString()); }

    @Test
    void TestRemoveEdgeLeavesItsDestinationVertex() {
        Graph g = new Graph();
        Vertex v = new Vertex("A");
        Edge e = new Edge(new Vertex("B"), 1);
        g.addEdge(v, e);
        g.removeEdge(v, e);

        String expected =
                "A -> \n" +
                "B -> \n";

        assertEquals(expected, g.toString());
    }

    @Test
    void TestRemoveVertexAlsoRemovesAllAssociatedEdges() {
        Graph g = new Graph();
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        g.addEdge(v1, v2, 1);
        g.addEdge(v1, v2, 2);
        g.addEdge(v2, v1, 4);
        g.addEdge(v2, v3, 3);

        String expectedBefore =
                "A -> (B, 1), (B, 2)\n" +
                "B -> (A, 4), (C, 3)\n" +
                "C -> \n";

        assertEquals(expectedBefore, g.toString());

        g.removeVertex(v2);

        String expectedAfter =
                "A -> \n" +
                "C -> \n";

        assertEquals(expectedAfter, g.toString());
    }

    @Test
    void TestRemoveVertexFromTertiaryUnidirectionalCycleSuccess() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 2);
        g.addEdge("C", "A", 3);
        g.addEdge("A", "C", 4);
        g.addEdge("C", "B", 5);
        g.addEdge("B", "A", 6);

        String expectedBefore =
                "A -> (B, 1), (C, 4)\n" +
                "B -> (A, 6), (C, 2)\n" +
                "C -> (A, 3), (B, 5)\n";

        assertEquals(expectedBefore, g.toString());

        g.removeVertex(new Vertex("B"));

        String expectedAfter =
                "A -> (C, 4)\n" +
                "C -> (A, 3)\n";

        assertEquals(expectedAfter, g.toString());
    }

    @Test
    void TestRemoveVertexFromQuadraticCompleteBiDiGraphSuccess() {
        Graph g = new Graph();
        g.addEdge("A", "A", 0);
        g.addEdge("A", "B", 1);
        g.addEdge("A", "C", 2);
        g.addEdge("A", "D", 3);
        g.addEdge("B", "B", 4);
        g.addEdge("B", "C", 5);
        g.addEdge("B", "D", 6);
        g.addEdge("B", "A", 7);
        g.addEdge("C", "C", 8);
        g.addEdge("C", "D", 9);
        g.addEdge("C", "A", 0);
        g.addEdge("C", "B", 1);
        g.addEdge("D", "D", 2);
        g.addEdge("D", "A", 3);
        g.addEdge("D", "B", 4);
        g.addEdge("D", "C", 5);

        String expectedBefore =
                "A -> (A, 0), (B, 1), (C, 2), (D, 3)\n" +
                "B -> (A, 7), (B, 4), (C, 5), (D, 6)\n" +
                "C -> (A, 0), (B, 1), (C, 8), (D, 9)\n" +
                "D -> (A, 3), (B, 4), (C, 5), (D, 2)\n";

        assertEquals(expectedBefore, g.toString());

        g.removeVertex(new Vertex("B"));
        g.removeVertex(new Vertex("D"));

        String expectedAfter =
                "A -> (A, 0), (C, 2)\n" +
                "C -> (A, 0), (C, 8)\n";

        assertEquals(expectedAfter, g.toString());
    }
    @Test
    void TestGetKeysSortedReturnsKeysSortedByVertexLabel() {
        Graph g = new Graph();
        g.addVertex("D");
        g.addVertex("C");
        g.addVertex("A");
        g.addVertex("B");

        String expected = "[A, B, C, D]";

        ArrayList<String> keys = g.getKeysSorted().stream().map(Vertex::getLabel).collect(Collectors.toCollection(ArrayList::new));

        assertEquals(expected, keys.toString());
    }

    @Test
    void TestGetEdgesSortedReturnsEdgesSortedByDestinationAndThenWeight() {
        Graph g = new Graph();
        Vertex v = new Vertex("A");
        g.addEdge(v, new Vertex("C"), 3);
        g.addEdge(v, new Vertex("D"), 4);
        g.addEdge(v, new Vertex("B"), 9);
        g.addEdge(v, new Vertex("B"), 2);

        String expected = "[(B, 2), (B, 9), (C, 3), (D, 4)]";

        assertEquals(expected, g.getEdgesSorted(v).toString());
    }
}

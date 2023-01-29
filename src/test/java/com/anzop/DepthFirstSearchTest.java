package com.anzop;

import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DepthFirstSearchTest {

    @Test
    void TestTraverseSimple() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, B, C";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("A")).getPath());
        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestTraverseSimpleDoNotLoop() {
        Graph g = new Graph();
        g.addEdge("A", "A");
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, B, C";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("A")).getPath());
        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestTraverseAlwaysTraverseVertexAndEdgesValueInAlphabeticOrder() {
        Graph g = new Graph();
        g.addEdge("C", "D");
        g.addEdge("B", "C");
        g.addEdge("A", "B");
        g.addEdge("D", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, B, C, D";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("A")).getPath());
        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestTraverseWhileNextWhenAtDeadEnd() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addVertex("E");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "E";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("E")).getPath());
    }

    @Test
    void TestTraverseWhileNextFromNonexistentVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("C")).getPath());
    }

    @Test
    void TestFullTraverseWillAttemptAllStartingVertices() {
        Graph g = new Graph();
        g.addEdge("A", "D");
        g.addEdge("B", "D");
        g.addEdge("C", "D");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, D, B, C";

        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestFullTraverseWillAlsoTraverseDisconnectedVerticesAndEdges() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "D");
        g.addEdge("C", "E");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, B, D, C, E";

        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestTraverseFromBeginningIntoTheSameVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A";

        assertEquals(expected, dfs.traverseInto(new Vertex("A"), new Vertex("A")).getPath());
    }

    @Test
    void TestTraverseIntoSeekIntoStartingVertexAndTraverseIntoTheSameVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "C";

        assertEquals(expected, dfs.traverseInto(new Vertex("C"), new Vertex("C")).getPath());
    }

    @Test
    void TestTraverseIntoMayLoopOverUnlessVisited() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "C, D, A, B";

        assertEquals(expected, dfs.traverseInto(new Vertex("C"), new Vertex("B")).getPath());
    }

    @Test
    void TestTraverseIntoDisconnectedVertexTriesAllAvailableVerticesInPath() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        g.addEdge("D", "E");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "B, C, A";

        assertEquals(expected, dfs.traverseInto(new Vertex("B"), new Vertex("E")).getPath());
    }

    @Test
    void TestTraverseIntoNonexistentVertexTriesAllAvailableVerticesInPath() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "B, C, D, A";

        assertEquals(expected, dfs.traverseInto(new Vertex("B"), new Vertex("E")).getPath());
    }

    @Test
    void TestTraverseIntoFromNonexistentVertexToTheSameVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "";

        assertEquals(expected, dfs.traverseInto(new Vertex("E"), new Vertex("E")).getPath());
    }

    @Test
    void TestTraverseIntoFromNonexistentVertexToAnotherNonexistentVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "";

        assertEquals(expected, dfs.traverseInto(new Vertex("E"), new Vertex("F")).getPath());
    }

    @Test
    void TestTraverseIntoFromNonexistentVertexIntoExistingVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "";

        assertEquals(expected, dfs.traverseInto(new Vertex("E"), new Vertex("A")).getPath());
    }

    @Test
    void TestFindComponentsEmptyGraph() {
        Graph g = new Graph();

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        dfs.fullTraverse();

        assertEquals(Collections.emptyList(), dfs.getComponents());
    }

    @Test
    void TestFindComponentsMinimalGraph() {
        Graph g = new Graph();
        g.addVertex("A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        dfs.fullTraverse();

        assertEquals(Collections.singletonList(0), dfs.getComponents());
    }

    @Test
    void TestFindComponentsOneComponent() {
        Graph g = new Graph();

        g.addBidirectionalEdge("A", "B");
        g.addBidirectionalEdge("B", "C");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        dfs.fullTraverse();

        assertEquals(Collections.singletonList(0), dfs.getComponents());
    }

    @Test
    void TestFindComponentsFromWhenOneIsACycle() {
        Graph g = new Graph();
        g.addBidirectionalEdge("A", "B");
        g.addBidirectionalEdge("B", "C");
        g.addBidirectionalEdge("C", "A");

        g.addBidirectionalEdge("I", "J");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        dfs.fullTraverse();

        assertEquals(Arrays.asList(0, 1),  dfs.getComponents());
    }

    @Test
    void TestFindComponentsWhenComponentHasLinkBack() {
        Graph g = new Graph();
        g.addBidirectionalEdge("A", "B");

        g.addBidirectionalEdge("I", "J");

        /*
            U belongs to the same component as A
            discovering this in one pass from an adjacency list requires a bidi graph
         */
        g.addBidirectionalEdge("U", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        dfs.fullTraverse();

        assertEquals(Arrays.asList(0, 1), dfs.getComponents());
    }
}

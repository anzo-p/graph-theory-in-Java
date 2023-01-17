package com.anzop;

import com.anzop.graph.Graph;
import com.anzop.graph.Vertex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DepthFirstSearchTest {

    @Test
    void TestTraverseSimple() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, B, C";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("A")).getPath());
        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestTraverseSimpleDoNotLoop() {
        Graph g = new Graph();
        g.addEdge("A", "A", 1);
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, B, C";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("A")).getPath());
        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestTraverseAlwaysTraverseVertexAndEdgesValueInAlphabeticOrder() {
        Graph g = new Graph();
        g.addEdge("C", "D", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("A", "B", 1);
        g.addEdge("D", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, B, C, D";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("A")).getPath());
        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestTraverseWhileNextWhenAtDeadEnd() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addVertex("E");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "E";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("E")).getPath());
    }

    @Test
    void TestTraverseWhileNextFromNonexistentVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "";

        assertEquals(expected, dfs.traverseWhileNext(new Vertex("C")).getPath());
    }

    @Test
    void TestFullTraverseWillAttemptAllStartingVertices() {
        Graph g = new Graph();
        g.addEdge("A", "D", 1);
        g.addEdge("B", "D", 1);
        g.addEdge("C", "D", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, D, B, C";

        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestFullTraverseWillAlsoTraverseDisconnectedVerticesAndEdges() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "D", 1);
        g.addEdge("C", "E", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A, B, D, C, E";

        assertEquals(expected, dfs.fullTraverse().getPath());
    }

    @Test
    void TestTraverseFromBeginningIntoTheSameVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "D", 1);
        g.addEdge("D", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "A";

        assertEquals(expected, dfs.traverseInto(new Vertex("A"), new Vertex("A")).getPath());
    }

    @Test
    void TestTraverseIntoSeekIntoStartingVertexAndTraverseIntoTheSameVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "D", 1);
        g.addEdge("D", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "C";

        assertEquals(expected, dfs.traverseInto(new Vertex("C"), new Vertex("C")).getPath());
    }

    @Test
    void TestTraverseIntoMayLoopOverUnlessVisited() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "D", 1);
        g.addEdge("D", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "C, D, A, B";

        assertEquals(expected, dfs.traverseInto(new Vertex("C"), new Vertex("B")).getPath());
    }

    @Test
    void TestTraverseIntoDisconnectedVertexTriesAllAvailableVerticesInPath() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "A", 1);
        g.addEdge("D", "E", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "B, C, A";

        assertEquals(expected, dfs.traverseInto(new Vertex("B"), new Vertex("E")).getPath());
    }

    @Test
    void TestTraverseIntoNonexistentVertexTriesAllAvailableVerticesInPath() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "D", 1);
        g.addEdge("D", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "B, C, D, A";

        assertEquals(expected, dfs.traverseInto(new Vertex("B"), new Vertex("E")).getPath());
    }

    @Test
    void TestTraverseIntoFromNonexistentVertexToTheSameVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "D", 1);
        g.addEdge("D", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "";

        assertEquals(expected, dfs.traverseInto(new Vertex("E"), new Vertex("E")).getPath());
    }

    @Test
    void TestTraverseIntoFromNonexistentVertexToAnotherNonexistentVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "D", 1);
        g.addEdge("D", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "";

        assertEquals(expected, dfs.traverseInto(new Vertex("E"), new Vertex("F")).getPath());
    }

    @Test
    void TestTraverseIntoFromNonexistentVertexIntoExistingVertex() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "D", 1);
        g.addEdge("D", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        String expected = "";

        assertEquals(expected, dfs.traverseInto(new Vertex("E"), new Vertex("A")).getPath());
    }

    @Test
    void TestFindComponentsEmptyGraph() {
        Graph g = new Graph();

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        assertEquals(0, dfs.findComponents());
    }

    @Test
    void TestFindComponentsMinimalGraph() {
        Graph g = new Graph();
        g.addVertex("A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        assertEquals(1, dfs.findComponents());
    }

    @Test
    void TestFindComponentsOneComponent() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        assertEquals(1, dfs.findComponents());
    }

    @Test
    void TestFindComponentsFromThreeComponents() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 1);
        g.addEdge("C", "A", 1);

        g.addEdge("I", "J", 1);

        g.addEdge("U", "V", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        assertEquals(3, dfs.findComponents());
    }

    @Test
    void TestFindComponentsWhenComponentHasLateLinkBackInDirectedGraph() {
        Graph g = new Graph();
        g.addEdge("A", "B", 1);

        g.addEdge("I", "J", 1);

        g.addEdge("U", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        assertEquals(3, dfs.findComponents());
    }

    @Test
    void TestFindComponentsWhenComponentHaLinkBackInBiDiGraph() {
        Graph g = new Graph();
        g.addBidirectionalEdge("A", "B", 1);

        g.addBidirectionalEdge("I", "J", 1);

        g.addBidirectionalEdge("U", "A", 1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        assertEquals(2, dfs.findComponents());
    }
}

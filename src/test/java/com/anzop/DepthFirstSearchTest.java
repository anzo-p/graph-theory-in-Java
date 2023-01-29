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

        assertEquals(Collections.emptyList(), dfs.showComponents());
    }

    @Test
    void TestFindComponentsMinimalGraph() {
        Graph g = new Graph();
        g.addVertex("A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        dfs.fullTraverse();

        assertEquals(Collections.singletonList(0), dfs.showComponents());
    }

    @Test
    void TestFindComponentsOneComponent() {
        Graph g = new Graph();

        g.addBidirectionalEdge("A", "B");
        g.addBidirectionalEdge("B", "C");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        dfs.fullTraverse();

        assertEquals(Collections.singletonList(0), dfs.showComponents());
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

        assertEquals(Arrays.asList(0, 1), dfs.showComponents());
    }

    @Test
    void TestFindComponentsWhenComponentHasLinkBack() {
        Graph g = new Graph();
        g.addBidirectionalEdge("A", "B");

        g.addBidirectionalEdge("I", "J");

        /*
            U belongs to the same component as A
            discovering this in one pass from an adjacency list requires a bidi graph
            as only this makes all of A, B, and U discoverable while traversing their shared component
         */
        g.addBidirectionalEdge("U", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        dfs.fullTraverse();

        assertEquals(Arrays.asList(0, 1), dfs.showComponents());
    }

    @Test
    void TestAllBridgesIntermediateArticulations() {
        /*
            A -- B -- C -- D
         */
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Arrays.asList("B", "C"), dfs.showArticulationPoints());
        assertEquals(Arrays.asList("A => B", "B => C", "C => D"), dfs.showBridges());
    }

    @Test
    void TestAllBridgesCenterArticulation() {
        /*
            A -- B -- C
                  \
                   D
         */
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("B", "D");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Collections.singletonList("B"), dfs.showArticulationPoints());
        assertEquals(Arrays.asList("A => B", "B => C, D"), dfs.showBridges());
    }

    @Test
    void TestBridgeAndArticulationCycleBeginning() {
        /*
               A -- D
             /  \
            B -- C

         */
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        g.addEdge("A", "D");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Collections.singletonList("A"), dfs.showArticulationPoints());
        assertEquals(Collections.singletonList("A => D"), dfs.showBridges());
    }

    @Test
    void TestArticulationAndBridgeFromCycle() {
        /*
            A -- B
             \  /
              C -- D
         */
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        g.addEdge("C", "D");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Collections.singletonList("C"), dfs.showArticulationPoints());
        assertEquals(Collections.singletonList("C => D"), dfs.showBridges());
    }

    @Test
    void TestBridgeIntoArticulationAndCycle() {
        /*
            C -- D -- E
                  \  /
                   F
         */

        Graph g = new Graph();
        g.addEdge("C", "D");
        g.addEdge("D", "E");
        g.addEdge("E", "F");
        g.addEdge("F", "D");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Collections.singletonList("D"), dfs.showArticulationPoints());
        assertEquals(Collections.singletonList("C => D"), dfs.showBridges());
    }

    @Test
    void TestDoubleArticulationBridgeConnectsTwoCycles() {
        /*
            A -- B
             \  /
               C -- D -- E
                     \  /
                      F
         */
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        g.addEdge("C", "D");
        g.addEdge("D", "E");
        g.addEdge("E", "F");
        g.addEdge("F", "D");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Arrays.asList("C", "D"), dfs.showArticulationPoints());
        assertEquals(Collections.singletonList("C => D"), dfs.showBridges());
    }

    @Test
    void TestTwoConnectedCyclesNoBridgeArticulationIsFirstVertex() {
        /*
                  D
                /  \
               A -- E
             /  \
            B -- C
         */
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        g.addEdge("A", "D");
        g.addEdge("D", "E");
        g.addEdge("E", "A");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Collections.singletonList("A"), dfs.showArticulationPoints());
        assertEquals(Collections.emptyList(), dfs.showBridges());
    }

    @Test
    void TestTwoConnectedCyclesNoBridgeArticulationIsNonInitialVertex() {
        /*
            A -- B
             \  /
              C -- D
               \  /
                E
         */
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        g.addEdge("C", "D");
        g.addEdge("D", "E");
        g.addEdge("E", "C");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Collections.emptyList(), dfs.showBridges());
        assertEquals(Collections.singletonList("C"), dfs.showArticulationPoints());
    }

    @Test
    void TestNoArticulationsOrBridges() {
        /*
            A -- B
             \  / \
              C -- D   we are encoding B -> C -> A and E -> C -> D
               \  /    this means that C cannot know of association between B - D
                E      C alone will not know that it is NOT an articulation point
         */
        Graph g = new Graph();
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        g.addEdge("C", "D");
        g.addEdge("D", "E");
        g.addEdge("E", "C");
        //g.addEdge("D", "B");
        g.addEdge("B", "D");

        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.fullTraverse();

        assertEquals(Collections.emptyList(), dfs.showBridges());

        System.out.println(dfs.showArticulationPoints());

        /*
            Cutting out C
            - won't increase the number of components in the graph.
            - will separate the cycles A -> B -> C -> A and C -> D -> E -> C, though.

            Will leave it undefined for now.
         */
    }
}

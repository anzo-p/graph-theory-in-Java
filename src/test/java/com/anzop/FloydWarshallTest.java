package com.anzop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloydWarshallTest {

    FloydWarshall init() {
        double[][] m = FloydWarshall.makeMatrix(7);
        m[0][1] =  2;
        m[0][2] =  5;
        m[0][6] = 10;
        m[1][2] =  2;
        m[1][4] = 11;
        m[2][6] =  2;
        m[6][5] = 11;
        m[4][5] =  1;
        m[5][4] = -2;

        FloydWarshall fw = new FloydWarshall(m);

        fw.solve();

        return fw;
    }

    @Test
    void TestFloydWarshallInitialImmediateStepRemainsShortestPath() {
        assertEquals(Arrays.asList(0, 1), init().getShortestPath(0, 1));
    }

    @Test
    void TestFloydWarshallInitialPathRemainsShortestPath() {
        assertEquals(Arrays.asList(0, 1, 2), init().getShortestPath(0, 2));
    }

    @Test
    void TestFloydWarshallNonexistentPath() {
        assertEquals(Collections.emptyList(), init().getShortestPath(0, 3));
    }

    @Test
    void TestFloydWarshallMultipleSideStepsMakeShortestRoute() {
        assertEquals(Arrays.asList(0, 1, 2, 6), init().getShortestPath(0, 6));
    }

    @Test
    void TestFloydWarshallIntoNegativeEdge() {
        assertEquals(Collections.emptyList(), init().getShortestPath(1, 4));
    }

    @Test
    void TestFloydWarshallAffectedByNegativeCycle() {
        assertEquals(Collections.emptyList(), init().getShortestPath(2, 5));
    }
}

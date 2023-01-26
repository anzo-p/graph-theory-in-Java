package com.anzop;

/*
    Floyd Warshall

    - Works with - Bidirectional, Cyclic, Positive or Negative Weights

    - Finds optimal paths between all vertices in one go
      - for all vertices that are connected
      - a path affected by a negative cycle means that there is no 'shortest' path based on weight alone
      - still important that the algorithm won't hang and also will detect the negative cycles

    Time Complexity O(V^3) - heavy, though takes the most out of the cubed time
    Space Complexity O(V^2)
 */


import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {

    private final double[][] graph;
    private final Double[][] distances;
    private final Integer[][] nextIndex;

    private void initialize() {
        int n = graph.length;

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {

                distances[i][j] = graph[i][j];

                if (distances[i][j] != Double.POSITIVE_INFINITY) {
                    nextIndex[i][j] = j;
                }
            }
        }
    }

    private void compute(Boolean detectNegativeCycle) {
        int n = graph.length;

        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {

                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        if (detectNegativeCycle) {
                            distances[i][j] = Double.NEGATIVE_INFINITY;
                            nextIndex[i][j] = -1;
                        } else {
                            distances[i][j] = distances[i][k] + distances[k][j];
                            nextIndex[i][j] = nextIndex[i][k];
                        }
                    }
                }
            }
        }
    }

    public FloydWarshall(double[][] graph) {
        this.graph = graph;

        int n = graph.length;
        distances = new Double[n][n];
        nextIndex = new Integer[n][n];
    }

    public void solve() {
        initialize();

        compute(false);
        compute(true);
    }

    public List<Integer> getShortestPath(int start, int end) {
        List<Integer> path = new ArrayList<>();

        if (distances[start][end] == Double.POSITIVE_INFINITY || nextIndex[start][end] == -1) {
            return path;
        }

        path.add(start);
        while (start != end) {
            start = nextIndex[start][end];
            path.add(start);
        }

        return path;
    }

    public static double[][] makeMatrix(int n) {
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(matrix[i], Double.POSITIVE_INFINITY);
            matrix[i][i] = 0;
        }

        return matrix;
    }
}

package com.anzop.graph;

import java.util.Objects;

public class Edge implements Comparable<Edge> {
    private final Vertex destination;

    private final int weight;

    public Edge(Vertex destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "(" + destination.getLabel() + ", " + weight + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Edge comparison = (Edge) o;
        return Objects.equals(destination, comparison.destination) &&
                Objects.equals(weight, comparison.weight);
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}

package com.anzop.graph;

import java.util.Objects;

public class Edge {
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
        return "(" + this.destination.getLabel() + ", " + this.weight + ")";
    }

    @Override
    public int hashCode() {
        return (destination.hashCode() + ~weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Edge comparison = (Edge) o;
        return Objects.equals(weight, comparison.weight) && Objects.equals(destination, comparison.destination);
    }

}

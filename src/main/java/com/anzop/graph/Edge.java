package com.anzop.graph;

public class Edge {
    private final Vertex destination;

    private int weight;

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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + this.destination.getLabel() + ", " + this.weight + ")";
    }
}

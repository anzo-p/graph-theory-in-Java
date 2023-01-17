package com.anzop.graph;

// example path from Computerphile, see https://en.wikipedia.org/wiki/Depth-first_search

public class ComplexExample {

    Graph g;

    public Graph get() {
        return g;
    }

    public ComplexExample() {
        g = new Graph();

        g.addEdge("S", "A", 7);
        g.addEdge("S", "B", 2);
        g.addEdge("S", "C", 3);

        g.addEdge("A", "S", 7);
        g.addEdge("A", "B", 3);
        g.addEdge("A", "D", 4);

        g.addEdge("B", "S", 2);
        g.addEdge("B", "A", 3);
        g.addEdge("B", "D", 4);
        g.addEdge("B", "H", 1);

        g.addEdge("C", "S", 3);
        g.addEdge("C", "L", 2);

        g.addEdge("D", "A", 4);
        g.addEdge("D", "B", 4);
        g.addEdge("D", "F", 5);

        g.addEdge("F", "D", 5);
        g.addEdge("F", "H", 3);

        g.addEdge("G", "H", 2);
        g.addEdge("G", "E", 2);

        g.addEdge("H", "B", 1);
        g.addEdge("H", "F", 3);
        g.addEdge("H", "G", 2);

        g.addEdge("I", "J", 6);
        g.addEdge("I", "L", 4);
        g.addEdge("I", "K", 4);

        g.addEdge("J", "I", 6);
        g.addEdge("J", "L", 4);
        g.addEdge("J", "K", 4);

        g.addEdge("K", "E", 5);
        g.addEdge("K", "I", 4);
        g.addEdge("K", "J", 4);

        g.addEdge("L", "C", 2);
        g.addEdge("L", "I", 4);
        g.addEdge("L", "J", 4);

        g.addEdge("E", "G", 2);
        g.addEdge("E", "K", 5);
    }
}
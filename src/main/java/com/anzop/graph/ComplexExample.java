package com.anzop.graph;

// example path from Computerphile, see https://www.youtube.com/watch?v=GazC3A4OQTE

public class ComplexExample {

    Graph g;

    public Graph get() {
        return g;
    }

    public ComplexExample() {
        g = new Graph();

        g.addBidirectionalEdge("S", "A", 7);
        g.addBidirectionalEdge("S", "B", 2);
        g.addBidirectionalEdge("S", "C", 3);

        g.addBidirectionalEdge("A", "S", 7);
        g.addBidirectionalEdge("A", "B", 3);
        g.addBidirectionalEdge("A", "D", 4);

        g.addBidirectionalEdge("B", "S", 2);
        g.addBidirectionalEdge("B", "A", 3);
        g.addBidirectionalEdge("B", "D", 4);
        g.addBidirectionalEdge("B", "H", 1);

        g.addBidirectionalEdge("C", "S", 3);
        g.addBidirectionalEdge("C", "L", 2);

        g.addBidirectionalEdge("D", "A", 4);
        g.addBidirectionalEdge("D", "B", 4);
        g.addBidirectionalEdge("D", "F", 5);

        g.addBidirectionalEdge("F", "D", 5);
        g.addBidirectionalEdge("F", "H", 3);

        g.addBidirectionalEdge("G", "H", 2);
        g.addBidirectionalEdge("G", "E", 2);

        g.addBidirectionalEdge("H", "B", 1);
        g.addBidirectionalEdge("H", "F", 3);
        g.addBidirectionalEdge("H", "G", 2);

        g.addBidirectionalEdge("I", "J", 6);
        g.addBidirectionalEdge("I", "L", 4);
        g.addBidirectionalEdge("I", "K", 4);

        g.addBidirectionalEdge("J", "I", 6);
        g.addBidirectionalEdge("J", "L", 4);
        g.addBidirectionalEdge("J", "K", 4);

        g.addBidirectionalEdge("K", "E", 5);
        g.addBidirectionalEdge("K", "I", 4);
        g.addBidirectionalEdge("K", "J", 4);

        g.addBidirectionalEdge("L", "C", 2);
        g.addBidirectionalEdge("L", "I", 4);
        g.addBidirectionalEdge("L", "J", 4);

        g.addBidirectionalEdge("E", "G", 2);
        g.addBidirectionalEdge("E", "K", 5);
    }
}
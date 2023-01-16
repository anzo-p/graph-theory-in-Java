package com.anzop;

import com.anzop.graph.Graph;

public class App {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge("A", "C", 1);
        g.addEdge("A", "B", 11);

        System.out.println(g);
    }
}

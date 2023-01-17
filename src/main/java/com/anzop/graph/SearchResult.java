package com.anzop.graph;

public class SearchResult {
    private final String path;
    private final int cost;

    public SearchResult(String path, int cost) {
        this.path = path;
        this.cost = cost;
    }

    public String getPath() {
        return path;
    }

    public int getCost() {
        return cost;
    }
}
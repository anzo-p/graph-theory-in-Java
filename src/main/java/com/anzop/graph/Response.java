package com.anzop.graph;

public class Response {
    private final String path;
    private final int cost;

    public Response(String path, int cost) {
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
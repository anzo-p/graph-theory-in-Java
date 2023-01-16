package com.anzop.graph;

import java.util.Objects;

public class Vertex {
    private final String label;

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Vertex comparison = (Vertex) o;
        return Objects.equals(label, comparison.label);
    }
}
package com.anzop.graph;

import java.util.Objects;
import java.util.Optional;

public class Vertex {
    private final String label;

    private Optional<Integer> groupCode = Optional.empty();

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Optional<Integer> getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(int groupCode) {
        this.groupCode = Optional.of(groupCode);
    }

    @Override
    public String toString() {
        // fixme need to decide group code presentation
        return label + '|' + groupCode.orElse(0);
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

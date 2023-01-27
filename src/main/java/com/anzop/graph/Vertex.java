package com.anzop.graph;

import java.util.Objects;

public class Vertex {
    private final String label;

    private Integer groupCode;

    private String color;

    public Vertex(String label) {
        this(label, 0, "green");
    }

    public Vertex(String label, int groupCode) {
        this(label, groupCode, "green");
    }

    public Vertex(String label, int groupCode, String color) {
        this.label = label;
        this.groupCode = groupCode;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public Integer getGroupCode() {
        return groupCode;
    }

    public String getColor() {
        return color;
    }

    public void setGroupCode(int groupCode) {
        this.groupCode = groupCode;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        // fixme need to decide group code presentation
        // label, group number, 'color', lowest link
        // A <1, Blue, A> => (B, 1), (C, 11)
        return label + " <" + groupCode + ">";
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

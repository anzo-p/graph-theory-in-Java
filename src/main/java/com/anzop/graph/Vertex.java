package com.anzop.graph;

import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
    private final String label;

    private Integer groupCode;

    private String color;

    private int linkSerial;

    private int lowestLink;

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

    public int getLowestLink() {
        return lowestLink;
    }

    public int getLinkSerial() {
        return linkSerial;
    }

    public void setGroupCode(int groupCode) {
        this.groupCode = groupCode;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLinkSerial(int linkSerial) {
        this.linkSerial = linkSerial;
    }

    public void setLowestLink(int lowestLink) {
        this.lowestLink = lowestLink;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Vertex comparison = (Vertex) o;
        return Objects.equals(label, comparison.label);
    }

    @Override
    public int compareTo(Vertex o) {
        return label.compareTo(o.label);
    }
}

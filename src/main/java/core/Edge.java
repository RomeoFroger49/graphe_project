package core;

import java.util.Objects;

public class Edge {
    int id;
    Vertex start;
    Vertex end;
    boolean directed;
    int weight;

    static int idCounter = 0;

    public Edge(Vertex start, Vertex end, boolean directed, int weight) {
        this.id = idCounter++;
        this.start = start;
        this.end = end;
        this.directed = directed;
        this.weight = weight;
    }


    public int getId() {
        return id;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public boolean isDirected() {
        return directed;
    }

    public int getWeight() {
        return weight;
    }

    // utils oriented graphs
    public boolean isConsecutive(Edge e) {
        return this.end == e.start || this.start == e.end;
    }

    // debogage visuel
    @Override
    public String toString() {
        return start.getLabel() + " " + (directed ? "-> " : "-- ") + end.getLabel() + " (weight: " + weight + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and class type

        Edge e = (Edge) o;
        return (Objects.equals(e.end, end) && Objects.equals(e.start, start)) || (Objects.equals(e.start, end) && Objects.equals(e.end, start));
    }
}

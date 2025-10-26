package main.core;

import java.util.ArrayList;
import java.util.Objects;

public class Vertex {
    int id;
    String label;
    ArrayList<Edge> edges;

    static int idCounter = 0;

    public Vertex(String label) {
        this.id = idCounter++;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    // utils for non-oriented graphs
    public int getDegree() {
        return edges.size();
    }

    // utils for oriented graphs
    public int getDegreePlus() {
        int cnt = 0;
        for (Edge e : edges) {
            if (e.start == this) cnt++;
        }
        return cnt;
    }

    public int getDegreeMinus() {
        int cnt = 0;
        for (Edge e : edges) {
            if (e.end == this) cnt++;
        }
        return cnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and class type

        Vertex e = (Vertex) o;
        return Objects.equals(e.id, id);
    }

}

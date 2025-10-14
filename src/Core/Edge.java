package Core;

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
}

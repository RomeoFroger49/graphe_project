package Core;

public class Edge {
    int id;
    String label;
    Vertex start;
    Vertex end;
    boolean directed;
    int weight;

    static int idCounter = 0;

    public Edge(Vertex start, Vertex end, boolean directed, int weight, String label) {
        this.id = idCounter++;
        this.start = start;
        this.end = end;
        this.directed = directed;
        this.weight = weight;
        this.label = label;
    }
}

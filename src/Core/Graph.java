package Core;

public class Graph {
    Vertex[] vertices;
    Edge[] edges;
    String label;

    public Graph(Vertex[] vertices, Edge[] edges, String label) {}

    public Vertex[] getVertices() {
        return vertices;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public Edge getEdge(Vertex v1, Vertex v2) {
        for (Edge e : edges) {
            if ((e.start == v1 && e.end == v2) || (!e.directed && e.start == v2 && e.end == v1)) {
                return e;
            }
        }
        return null;
    }

    public Vertex getVertex(Vertex v) {
        for (Vertex vertex : vertices) {
            if (vertex == v) {
                return vertex;
            }
        }
        return null;
    }
}

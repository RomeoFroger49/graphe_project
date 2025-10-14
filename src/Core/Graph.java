package Core;

public class Graph {
    Vertex[] vertices;
    Edge[] edges;
    String label;

    public Graph(Vertex[] vertices, Edge[] edges, String label) {
        this.vertices = vertices;
        this.edges = edges;
        this.label = label;

        // set edges for each vertex
        for (Vertex v : vertices) {
            int cnt = 0;
            for (Edge e : edges) {
                if (e.start == v || (!e.directed && e.end == v) || (e.directed && e.end == v)) {
                    cnt++;
                }
            }
            Edge[] vEdges = new Edge[cnt];
            cnt = 0;
            for (Edge e : edges) {
                if (e.start == v || (!e.directed && e.end == v) || (e.directed && e.end == v)) {
                    vEdges[cnt++] = e;
                }
            }
            v.edges = vEdges;
        }
    }

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

    public boolean allVisited() {
        for (Vertex v : vertices) {
            if (!v.isVisited()) return false;
        }
        return true;
    }
}

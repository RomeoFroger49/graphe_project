package Core;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    ArrayList<Vertex> vertices;
    ArrayList<Edge> edges;
    String label;

    public Graph(Vertex[] vertices, Edge[] edges, String label) {
        this.vertices = new ArrayList<>();
        Collections.addAll(this.vertices, vertices);
        this.edges = new ArrayList<>();
        Collections.addAll(this.edges, edges);
        this.label = label;

        // set edges for each vertex
        setEdgesForVertices();
    }

    // constructeur à partir d'une matrice d'adjacence
    public Graph(int[][] matrice, String label) {

        this.label = label;
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        int n = matrice.length;
        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex(String.valueOf(i)));
        }

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] != 0) {
                    edges.add(new Edge(vertices.get(i), vertices.get(j), true, matrice[i][j]));
                }
            }
        }
        setEdgesForVertices();
    }

    // utils - affecte à chaque sommet la liste de ses arêtes
    public void setEdgesForVertices() {
        for (Vertex v : vertices) {
            v.edges = new ArrayList<>();
        }

        for (Edge e : edges) {
            // Pour le sommet de départ
            e.start.edges.add(e);

            // Pour le sommet d’arrivée, si l’arête est non orientée
            if (!e.directed && e.end != e.start) {
                e.end.edges.add(e);
            }

            // Si l’arête est orientée, on peut aussi ajouter e.end.edges.add(e)
            // si on veut que le sommet d’arrivée connaisse aussi ses arêtes entrantes
        }
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
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

    public boolean isAllVisited() {
        for (Vertex v : vertices) {
            if (!v.isVisited()) return false;
        }
        return true;
    }

    public void affichage() {
        for (Vertex v : this.getVertices()) {
            System.out.println("\nEdges for vertex " + v.getLabel() + ":");
            for (Edge e : v.getEdges()) {
                System.out.println(e);
            }
        }
    }
}

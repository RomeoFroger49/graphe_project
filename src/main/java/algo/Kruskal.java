package algo;

import core.Edge;
import core.Graph;
import core.Vertex;

import java.util.*;

public class Kruskal {
    public static List<Edge> mst(Graph g){

        // Récupération des sommets et arêtes
        ArrayList<Vertex> vertices = g.getVertices();
        ArrayList<Edge> edges = g.getEdges();

        // Trie des arêtes par poids croissant
        ArrayList<Edge> sorted = new ArrayList<>(edges);
        sorted.sort(Comparator.comparingInt(Edge::getWeight));

        // Union-Find (Disjoint Set Union) indexé par Vertex
        Map<Vertex, Integer> indexByVertex = new HashMap<>();
        for (int i = 0; i < vertices.size(); i++) {
            indexByVertex.put(vertices.get(i), i);
        }
        DSU dsu = new DSU(vertices.size());

        // Construction des arêtes du MST
        ArrayList<Edge> mstEdges = new ArrayList<>();
        for (Edge e : sorted) {
            // On ne garde que les arêtes non orientées pour les MST

            Integer iu = indexByVertex.get(e.getStart());
            Integer iv = indexByVertex.get(e.getEnd());
            if (iu == null || iv == null) continue; // sécurité

            if (dsu.find(iu) != dsu.find(iv)) {
                dsu.union(iu, iv);
                // on crée une nouvelle arête (non orientée) pour les MST
                mstEdges.add(new Edge(e.getStart(), e.getEnd(), /*directed=*/false, e.getWeight()));
            }
        }

        // Création d'un nouveau Graph à partir du même ensemble de Vertex et des arêtes MST
        Vertex[] vArr = vertices.toArray(new Vertex[0]);
        Edge[] eArr = mstEdges.toArray(new Edge[0]);
        return new Graph(vArr, eArr, g.getLabel() + " - MST (Kruskal)").getEdges();
    }


    private static final class DSU {

        private final int[] parent;
        private final int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return;
            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[ra] > rank[rb]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
        }
    }
}

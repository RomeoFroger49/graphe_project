package algo;

import core.Edge;
import core.Graph;
import core.Vertex;

import java.util.*;

/**
 * Kruskal minimal : construit un MST (ou une forêt si non connexe) et renvoie un nouveau Graph.
 * Hypothèses :
 *  - Graphe "non orienté" modélisé par arêtes dirigées en double (A->B et B->A).
 *  - Poids non négatifs.
 *  - On recrée des Edge non orientées (directed = false) dans l'arbre résultat.
 */
public final class Kruskal {

    private Kruskal() {}

    public static Graph mst(Graph g) {
        ArrayList<Vertex> vertices = g.getVertices();
        ArrayList<Edge> edges = g.getEdges();

        // Tri toutes les arêtes par poids (on ignore l'orientation)
        ArrayList<Edge> sorted = new ArrayList<>(edges);
        sorted.sort(Comparator.comparingInt(e -> e.getWeight()));

        // Index par identité (pas d'equals/hashCode requis)
        Map<Vertex, Integer> idx = new IdentityHashMap<>();
        for (int i = 0; i < vertices.size(); i++) idx.put(vertices.get(i), i);

        DSU dsu = new DSU(vertices.size());
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge e : sorted) {
            Integer iu = idx.get(e.getStart()), iv = idx.get(e.getEnd());
            if (iu == null || iv == null) continue; // sécurité (sommets hors graphe)
            if (dsu.find(iu) != dsu.find(iv)) {
                dsu.union(iu, iv);
                // Ajoute l'arête dans le MST comme non orientée
                mstEdges.add(new Edge(e.getStart(), e.getEnd(), /*directed=*/false, e.getWeight()));
            }
        }

        return new Graph(
                vertices.toArray(new Vertex[0]),
                mstEdges.toArray(new Edge[0]),
                g.getLabel() + " - MST (Kruskal minimal)"
        );
    }

    // --- DSU minimal (Union-Find) ---
    private static final class DSU {
        final int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
        void union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return;
            if (rank[ra] < rank[rb]) parent[ra] = rb;
            else if (rank[ra] > rank[rb]) parent[rb] = ra;
            else { parent[rb] = ra; rank[ra]++; }
        }
    }
}

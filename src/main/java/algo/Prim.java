package algo;

import core.Edge;
import core.Graph;
import core.Vertex;

import java.util.*;

/**
 * Prim minimaliste : construit un MST (ou une forêt si non connexe) et renvoie un nouveau Graph.
 * Hypothèses simples :
 *  - Graphe "non orienté" modélisé par deux arêtes dirigées opposées entre chaque paire de sommets.
 *  - Poids non négatifs.
 *  - On recrée des Edge non orientées (directed = false) dans l'arbre résultat.
 */
public final class Prim {

    private Prim() {}

    public static Graph mst(Graph g) {
        ArrayList<Vertex> vertices = g.getVertices();
        ArrayList<Edge> edges = g.getEdges();

        // Adjacence "non orientée" (on ajoute chaque arête dans les deux sens)
        Map<Vertex, List<Neighbor>> adj = new HashMap<>();
        for (Vertex v : vertices) adj.put(v, new ArrayList<>());
        for (Edge e : edges) {
            // On traite comme non orienté (ajout dans les deux sens)
            adj.get(e.getStart()).add(new Neighbor(e.getEnd(), e.getWeight(), e.getStart()));
            adj.get(e.getEnd()).add(new Neighbor(e.getStart(), e.getWeight(), e.getEnd()));
        }

        List<Edge> mstEdges = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Cand> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.w));

        // Gérer les composantes non connexes : on lance Prim depuis chaque sommet non visité
        for (Vertex start : vertices) {
            if (visited.contains(start)) continue;

            visited.add(start);
            for (Neighbor nb : adj.getOrDefault(start, Collections.emptyList())) {
                pq.offer(new Cand(start, nb.to, nb.w));
            }

            while (!pq.isEmpty()) {
                Cand c = pq.poll();
                if (visited.contains(c.to)) continue; // déjà absorbé

                // Ajouter l'arête au MST (non orientée)
                mstEdges.add(new Edge(c.from, c.to, /*directed=*/false, c.w));
                visited.add(c.to);

                // Étendre depuis le nouveau sommet
                for (Neighbor nb : adj.getOrDefault(c.to, Collections.emptyList())) {
                    if (!visited.contains(nb.to)) {
                        pq.offer(new Cand(c.to, nb.to, nb.w));
                    }
                }
            }
        }

        return new Graph(
                vertices.toArray(new Vertex[0]),
                mstEdges.toArray(new Edge[0]),
                g.getLabel() + " - MST (Prim minimal)"
        );
    }

    private static final class Neighbor {
        final Vertex to; final int w; @SuppressWarnings("unused") final Vertex from;
        Neighbor(Vertex to, int w, Vertex from) { this.to = to; this.w = w; this.from = from; }
    }

    private static final class Cand {
        final Vertex from, to; final int w;
        Cand(Vertex from, Vertex to, int w) { this.from = from; this.to = to; this.w = w; }
    }
}

package algo;

import core.Edge;
import core.Graph;
import core.Vertex;

import java.util.*;

/**
 * Version simplifiée de l'algorithme de Dijkstra.
 * Calcule le plus court chemin entre deux sommets dans un graphe orienté
 * (ou pseudo-non orienté représenté par des arêtes doubles).
 */
public final class Dijkstra {

    private Dijkstra() {}

    /**
     * Calcule le plus court chemin (liste de Vertex) entre source et cible.
     * @param g Graphe de départ
     * @param source Sommet de départ
     * @param target Sommet d’arrivée
     * @return Liste ordonnée des sommets du plus court chemin (vide si inatteignable)
     */
    public static List<Vertex> shortestPath(Graph g, Vertex source, Vertex target) {
        // Initialisation
        Map<Vertex, Integer> dist = new HashMap<>();
        Map<Vertex, Vertex> prev = new HashMap<>();
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (Vertex v : g.getVertices()) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
        }
        dist.put(source, 0);
        pq.add(source);

        // Dijkstra
        while (!pq.isEmpty()) {
            Vertex u = pq.poll();

            if (u == target) break; // on a trouvé le plus court chemin jusqu’à la cible

            for (Edge e : g.getEdges()) {
                if (e.getStart() == u) {
                    Vertex v = e.getEnd();
                    int newDist = dist.get(u) + e.getWeight();
                    if (newDist < dist.get(v)) {
                        dist.put(v, newDist);
                        prev.put(v, u);
                        pq.remove(v); // suppression si déjà présent pour mise à jour
                        pq.add(v);
                    }
                }
            }
        }

        // Reconstruction du chemin
        List<Vertex> path = new ArrayList<>();
        if (dist.get(target) == Integer.MAX_VALUE) return path; // pas de chemin

        for (Vertex at = target; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}

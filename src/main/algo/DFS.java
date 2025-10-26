package main.algo;
import main.core.*;

import java.util.*;

/**
 * DFS "comme le BFS", version itérative avec pile.
 * - Entrée : Vertex de départ
 * - Sortie : ordre de visite (List<Vertex>)
 * - Parcours : pour chaque arête incidente à v, on empile l’autre extrémité
 */
public final class DFS {

    private DFS() {}

    public static List<Vertex> dfs(Vertex start) {
        if (start == null) return List.of();

        List<Vertex> order = new ArrayList<>();
        Set<Vertex> visited = new LinkedHashSet<>();
        Deque<Vertex> stack = new ArrayDeque<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            Vertex v = stack.pop();
            if (!visited.add(v)) continue;        // déjà visité → on ignore
            order.add(v);

            // Empiler les voisins de v
            for (Edge e : v.getEdges()) {
                Vertex w = otherEnd(v, e);
                if (w != null && !visited.contains(w)) {
                    stack.push(w);
                }
            }
        }
        return order;
    }

    // Renvoie "l'autre extrémité" de l'arête e par rapport à v (si e est incidente à v), sinon null.
    private static Vertex otherEnd(Vertex v, Edge e) {
        Vertex a = e.getStart();
        Vertex b = e.getEnd();
        if (v.equals(a)) return b;
        if (v.equals(b)) return a;
        return null;
    }
}

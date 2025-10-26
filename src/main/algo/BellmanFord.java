package main.algo;
import main.core.*;
import java.util.*;

/**
 * Implémentation de l'algorithme de Bellman-Ford.
 * - Gère les poids négatifs (pas de cycles négatifs).
 * - Calcule les plus courts chemins depuis une source donnée.
 * - Retourne un Map<Vertex, Double> contenant les distances minimales.
 */
public final class BellmanFord {

    private BellmanFord() {}

    /**
     * Exécute Bellman-Ford sur le graphe donné depuis le sommet source.
     * @param graph Graphe sur lequel exécuter l'algorithme
     * @param source Sommet de départ
     * @return Un objet Result contenant les distances et les prédécesseurs
     * @throws IllegalStateException si un cycle négatif est détecté
     */
    public static Result run(Graph graph, Vertex source) {
        // Initialisation
        Map<Vertex, Double> dist = new HashMap<>();
        Map<Vertex, Vertex> pred = new HashMap<>();

        for (Vertex v : graph.getVertices()) {
            dist.put(v, Double.POSITIVE_INFINITY);
            pred.put(v, null);
        }
        dist.put(source, 0.0);

        int n = graph.getVertices().size();

        // Étape 1 : relaxation répétée (|V| - 1 fois)
        for (int i = 1; i <= n - 1; i++) {
            for (Edge e : graph.getEdges()) {
                Vertex u = e.getStart();
                Vertex v = e.getEnd();
                double w = e.getWeight();

                double newDist = dist.get(u) + w;
                if (dist.get(u) != Double.POSITIVE_INFINITY && newDist < dist.get(v)) {
                    dist.put(v, newDist);
                    pred.put(v, u);
                }
            }
        }

        // Étape 2 : détection de cycle négatif
        for (Edge e : graph.getEdges()) {
            Vertex u = e.getStart();
            Vertex v = e.getEnd();
            double w = e.getWeight();

            if (dist.get(u) != Double.POSITIVE_INFINITY && dist.get(u) + w < dist.get(v)) {
                throw new IllegalStateException("Cycle négatif détecté !");
            }
        }

        return new Result(dist, pred);
    }

    /** Structure de résultat : distances + prédécesseurs */
    public static class Result {
        private final Map<Vertex, Double> distances;
        private final Map<Vertex, Vertex> predecessors;

        public Result(Map<Vertex, Double> distances, Map<Vertex, Vertex> predecessors) {
            this.distances = distances;
            this.predecessors = predecessors;
        }

        public Map<Vertex, Double> getDistances() { return distances; }
        public Map<Vertex, Vertex> getPredecessors() { return predecessors; }

        /** Affichage lisible du résultat */
        public void print(Vertex source) {
            System.out.println("Résultats de Bellman-Ford depuis " + source.getLabel() + " :");
            for (Map.Entry<Vertex, Double> e : distances.entrySet()) {
                Vertex v = e.getKey();
                double d = e.getValue();
                System.out.printf("  %-10s : ", v.getLabel());
                if (d == Double.POSITIVE_INFINITY)
                    System.out.println("inaccessible");
                else
                    System.out.printf("%.1f\n", d);
            }
        }

        /** Reconstruit le chemin le plus court jusqu’à une destination donnée */
        public List<Vertex> getPathTo(Vertex dest) {
            List<Vertex> path = new ArrayList<>();
            for (Vertex v = dest; v != null; v = predecessors.get(v)) {
                path.add(v);
            }
            Collections.reverse(path);
            return path;
        }
    }
}
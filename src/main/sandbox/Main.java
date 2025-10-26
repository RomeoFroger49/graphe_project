package main.sandbox;

import main.algo.BellmanFord;
import main.core.Graph;
import main.core.Vertex;
import main.core.City;
import main.algo.BFS;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import main.algo.DFS;

public class Main {
    public static void main(String[] args) {

        int[][] matrice = {
                /*Paris*/   {0, 50, 70, 60, 0, 0, 0, 110, 150, 80},
                /*Caen*/    {50, 0, 65, 0, 0, 0, 0, 75, 0, 0},
                /*Lille*/   {70, 65, 0, 120, 100, 0, 0, 0, 0, 0},
                /*Dijon*/   {60, 0, 120, 0, 75, 70, 75, 0, 0, 0},
                /*Nancy*/   {0, 0, 100, 75, 0, 90, 80, 0, 0, 0},
                /*Lyon*/    {0, 0, 0, 70, 90, 0, 40, 0, 100, 0},
                /*Grenoble*/{0, 0, 0, 75, 80, 40, 0, 0, 0, 0},
                /*Rennes*/  {110, 75, 0, 0, 0, 0, 0, 0, 130, 45},
                /*Bordeaux*/{150, 0, 0, 0, 0, 100, 0, 130, 0, 90},
                /*Nantes*/  {80, 0, 0, 0, 0, 0, 0, 45, 90, 0},
        };

        Graph graph = new Graph(matrice, City.listLabels(), "test");

        System.out.println("=== BFS depuis Rennes ===");
        List<Vertex> bfs = BFS.bfsDirected(graph, graph.getVertexByName(City.RENNES.label()));
        bfs.forEach(v -> System.out.print(v.getLabel() + " "));
        System.out.println();

        System.out.println("\n=== DFS depuis Rennes ===");
        List<Vertex> dfs = DFS.dfs(graph.getVertexByName(City.RENNES.label()));
        dfs.forEach(v -> System.out.print(v.getLabel() + " "));
        System.out.println();

        System.out.println("\n=== Bellman-Ford depuis Rennes ===");
        Vertex start = graph.getVertexByName(City.RENNES.label());
        try {
            BellmanFord.Result res = BellmanFord.run(graph, start);
            res.print(start);

            // Exemple : afficher le chemin Rennes -> Bordeaux
            Vertex dest = graph.getVertexByName(City.BORDEAUX.label());
            List<Vertex> path = res.getPathTo(dest);
            System.out.println("\nChemin Rennes -> Bordeaux : ");
            path.forEach(v -> System.out.print(v.getLabel() + " "));
            System.out.println();

        } catch (IllegalStateException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}

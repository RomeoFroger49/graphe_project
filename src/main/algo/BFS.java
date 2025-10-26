package main.algo;

import main.core.Edge;
import main.core.Graph;
import main.core.Vertex;

import java.util.*;

public class BFS {

    @SuppressWarnings("unused")
    public static  List<Vertex> bfsDirected(Graph g, Vertex start) {

        Queue<Vertex> queue = new LinkedList<>();
        LinkedHashSet<Vertex> visited = new LinkedHashSet<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();

            for (Edge e : v.getEdges()) {
                Vertex w = e.getEnd();
                if (visited.add(w)) {
                    queue.add(w);
                }
            }
        }
        return visited.stream().toList();
    }
}

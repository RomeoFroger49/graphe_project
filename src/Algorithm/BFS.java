package Algorithm;

import Core.Edge;
import Core.Graph;
import Core.Vertex;

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

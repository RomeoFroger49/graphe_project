package Algorithm;

import Core.Edge;
import Core.Graph;
import Core.Vertex;

import java.util.*;

public class BFS {

    public static ArrayList<Vertex> non_oriented(Graph g, Vertex start) {
        Queue<Vertex> queue = new LinkedList<>();
        ArrayList<Vertex> path = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();

        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {

            Vertex v = queue.poll();
            path.add(v);

            for (Edge e : v.getEdges()) {

                Vertex w = (e.getStart() == v) ? e.getEnd() : e.getStart();

                if (visited.add(w)) {
                    queue.add(w);
                }
            }
        }
        return path;
    }

    public static  ArrayList<Vertex> oriented(Graph g, Vertex start) {

        Queue<Vertex> queue = new LinkedList<>();
        ArrayList<Vertex> path = new ArrayList<>();

        start.setVisited();
        queue.add(start);

        while (!queue.isEmpty()) {

            Vertex v = queue.poll();
            path.add(v);

            for (Edge e : v.getEdges()) {
                if (e.getEnd() == v) continue;

                Vertex toVisit = e.getEnd();
                if (e.getStart() == v && !toVisit.isVisited()) {
                    toVisit.setVisited();
                    queue.add(toVisit);
                }
            }
        }
        return path;
    }
}

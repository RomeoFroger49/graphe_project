package Algorithm;

import Core.Edge;
import Core.Graph;
import Core.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static ArrayList<Vertex> non_oriented(Graph g, Vertex start) {
        start.setVisited();
        Queue<Vertex> queue = new LinkedList<>();
        ArrayList<Vertex> path = new ArrayList<>();
        queue.add(start);
        path.add(start);
        while (!g.allVisited()) {
            Vertex v = queue.poll();
            for (Edge e : v.getEdges()) {
                Vertex toVisit = e.getStart() == v ? e.getEnd() : e.getStart();
                if (!toVisit.isVisited()) {
                    toVisit.setVisited();
                    queue.add(toVisit);
                    path.add(toVisit);
                }
            }
        }
        return path;
    }
}

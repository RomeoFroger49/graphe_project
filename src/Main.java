import Core.Graph;
import Core.Vertex;
import Data.City;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

        List<Vertex> result = Algorithm.BFS.bfsDirected(graph, graph.getVertexByName(City.RENNES.label()));

        for (Vertex v : result) {
            System.out.print(v.getLabel() + " ");
        }







    }
}
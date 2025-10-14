package Core;

public class Vertex {
    int id;
    String label;
    Edge[] edges;

    static int idCounter = 0;

    public Vertex(int id, String label) {
        this.id = idCounter++;
        this.label = label;
    }

}

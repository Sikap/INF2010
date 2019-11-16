import java.util.ArrayList;
import java.util.List;


public class Graph {

    private List<Node> nodes; // Noeuds
    private List<Edge> edges; // Les arcs

    public Graph() {
        // A compléter
		nodes = new ArrayList<>();
		edges = new ArrayList<>();
    }

    public List<Edge> getEdgesGoingFrom(Node source) {
        List<Edge> edgesGoingFrom = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                edgesGoingFrom.add(edge);
            }
        }

        return edgesGoingFrom;

    }

    public List<Edge> getEdgesGoingTo(Node dest) {
        List<Edge> edgesGoingTo = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getDestination().equals(dest))
                edgesGoingTo.add(edge);
        }
        return edgesGoingTo;
    }

    // Accesseurs
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;



public class Dijkstra {

	private Graph graph;
	private Map<Node, Edge> dijkstraTable[];
	private Stack<Edge> path;
	

	public Dijkstra (Graph g) {
		this.graph = g;
	}

	public void findPath(Node s, Node d) {

		dijkstraTable = new HashMap[graph.getNodes().size()];

		List <Edge> edge = graph.getEdgesGoingFrom(s);
		//dijkstraTable[0].put(s,);
		for(int i=1;i<dijkstraTable.length;i++) {

			//path.push()
			//dijkstraTable[i].put(,0);

		}
		// A compléter

		
	}

	private Node getMinimum(Map<Node, Edge> map) {
		Edge min = null;
		for (Node Key : map.keySet()) {
			if ( min == null || map.get(Key).getDistance() < min.getDistance()) {
				min = map.get(Key); 
			}
		}
		return min.getDestination();
	}

	private Edge getMinimum (Edge e1, Edge e2) {
		// A completer
		return null;
	}
	
	public String printShortPath(Node source, Node destination) {
		// A completer
		return null;
	}

	public void showTable() {
		// A completer
		
	}
}

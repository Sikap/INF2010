import java.util.*;


public class Dijkstra {

	private Graph graph;
	private Map<Node, Edge> dijkstraTable[];
	private Stack<Edge> path;


	public Dijkstra (Graph g) {
		this.graph = g;
	}

	public void findPath(Node s, Node d) {
		int nbNode=graph.getNodes().size();
		dijkstraTable = new HashMap[nbNode];
		Node curentNode = s;
		// First Iteration
		dijkstraTable[0]= new HashMap<>();
		dijkstraTable[0].put(s,new Edge(s,s,0));
		// N Iteration
		int index =1;
		Set <Node> visitedNodes = new HashSet<>();
		while (curentNode!=d) {
			visitedNodes.add(curentNode);
			dijkstraTable[index] = new HashMap<>(dijkstraTable[index-1]);
			dijkstraTable[index].remove(curentNode);
			List<Edge> edges = graph.getEdgesGoingFrom(curentNode);
			for (Edge i: edges)
			{
				Edge e = new Edge(i.getSource(),i.getDestination(),i.getDistance()+dijkstraTable[index-1].getOrDefault(curentNode,new Edge(i.getSource(),i.getDestination(),0)).getDistance());
				boolean isCurrentNodeVisited = visitedNodes.contains(i.getDestination());
				boolean hasRoute = dijkstraTable[index-1].containsKey(i.getDestination());
				if(!isCurrentNodeVisited && (!hasRoute || ( hasRoute &&  dijkstraTable[index - 1].get(i.getDestination()).getDistance() > e.getDistance() )))
				dijkstraTable[index].put(e.getDestination(),e);
			}

			curentNode = getMinimum(dijkstraTable[index]);
			index++;
		}
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

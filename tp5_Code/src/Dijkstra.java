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
				boolean isBetterRoute = ( hasRoute &&  dijkstraTable[index - 1].get(i.getDestination()).getDistance() > e.getDistance() );
				if(!isCurrentNodeVisited && (!hasRoute || isBetterRoute))
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
		if(e1==null||e2==null) {
			if (e1 == null && e2 == null) {
				return null;
			}else if (e1 == null) {
				return e2;
			}else if (e2 == null){
				return e1;
			}
		}
		return (e1.getDistance()>e2.getDistance())? e2:e1 ;
	}
	
	public String printShortPath(Node source, Node destination) {
		// A completer
		return null;
	}

	public void showTable() {
		// A completer
		path=new Stack<>();
		List<Edge> edges = new ArrayList<>();
		/*edges.addAll( dijkstraTable[0].values());
		Node trateNode=edges.get(0).getSource();*/
		Edge tmpEdge=null;
		int index= dijkstraTable.length;
		while(index!=0){
			edges.clear();
			if(dijkstraTable[--index]!=null){
				edges.addAll( dijkstraTable[index].values());
				tmpEdge=null;
				for(int i=0;i<edges.size();i++){
					tmpEdge=getMinimum(tmpEdge,edges.get(i));
				}
				path.push(tmpEdge);
			}
		}
		String info="";
		while (path.size()!=0){
			tmpEdge=path.pop();
			info=Integer.toString(tmpEdge.getDistance());
			info+=tmpEdge.getSource().getName();
			System.out.println( info);
		}
	}
}

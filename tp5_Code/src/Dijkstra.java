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
		findPath(source,destination);
		setPath();
		String info="";
		Stack<Node> stackNode=new Stack<>();
		Edge tmpEdge=null;
		Node tmpNode=null,arriverNode;
		tmpEdge=path.pop();
		arriverNode=tmpEdge.getSource();
		for (int i=0;i<path.size();i++) {
			tmpEdge=path.get(i);
			if(tmpNode==null||tmpNode==tmpEdge.getDestination()){
				stackNode.push(tmpEdge.getDestination());
				if(arriverNode.getName()==tmpEdge.getSource().getName()){
					break;
				}
				tmpNode=tmpEdge.getSource();
			}

		}
		stackNode.push(arriverNode);
		while (stackNode.size()!=0){
			info+=stackNode.pop().getName();
		}
		return info;
	}

	private void setPath(){
		path=new Stack<>();
		List<Edge> edges = new ArrayList<>();
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
	}

	public void showTable() {
		// A completer
		List<Edge> edges = new ArrayList<>();
		Edge tmpEdge=null;
		String txt="";
		int index =0,index2=0;
		while (index<dijkstraTable.length){
			txt="";
			if(dijkstraTable[index]!=null){
				edges.clear();
				edges.addAll( dijkstraTable[index].values());
				index2=0;
				for(int i =0;i<edges.size();i++){
					tmpEdge=edges.get(i);
					for(int y=index2;y<tmpEdge.getDestination().getId();y++){
						txt+="   ";
					}
					txt+=Integer.toString(tmpEdge.getDistance());
					txt+=tmpEdge.getSource().getName();
					txt+=" ";
					index2=tmpEdge.getDestination().getId()+1;

				}
			}
			System.out.println(txt);
			index++;
		}
	}
}

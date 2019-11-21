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
		findPath(source,destination);
		setPath();
		String info="";
		Edge tmpEdge=null;
		while (path.size()!=0){
			tmpEdge=path.pop();
			info+=tmpEdge.getSource().getName();
		}
		info+=tmpEdge.getDestination().getName();
		return info;
	}

	private void setPath(){
		path=new Stack<>();
		List<Edge> edges = new ArrayList<>();
		Edge tmpEdge=null;
		int index= dijkstraTable.length-1;
		while(index!=0){
			edges.clear();
			if(dijkstraTable[index]!=null){
				edges.addAll( dijkstraTable[index].values());
				tmpEdge=null;
				for(int i=0;i<edges.size();i++){
					tmpEdge=getMinimum(tmpEdge,edges.get(i));
				}
				path.push(tmpEdge);
				index=tmpEdge.getSource().getId();
			}
		}
	}

	public void showTable() {
		// A completer
		List<Edge> edges = new ArrayList<>();
		Edge tmpEdge=null;
		String txt="";
		int index =0,index2=0;
        List<Node> nodeMap=new ArrayList();
		System.out.println("Table dijkstra");
        while (index<dijkstraTable.length){
            edges.clear();
            if(dijkstraTable[index]!=null){
                edges.addAll( dijkstraTable[index].values());
                for(int i=0;i<edges.size();i++){
                    if(!nodeMap.contains(edges.get(i).getDestination())){
                        nodeMap.add(edges.get(i).getDestination());
                    }
                }
            }
            index++;
        }
        for (Node a: nodeMap
             ) {
            txt+=a.getName()+" |";
        }
        System.out.println(txt);
        index=0;
		while (index<dijkstraTable.length){
			txt="";
			if(dijkstraTable[index]!=null){
				edges.clear();
				edges.addAll( dijkstraTable[index].values());
				index2=0;
				int y=0;
				for(int i=0;i<edges.size();i++){
					tmpEdge=edges.get(i);
					for(y=index2;y<tmpEdge.getDestination().getId();y++){
						txt+="  |";
					}
					txt+=Integer.toString(tmpEdge.getDistance());
					txt+=tmpEdge.getSource().getName();
					txt+="|";
					index2=tmpEdge.getDestination().getId()+1;
				}
				for(;y<dijkstraTable.length-1;y++){
                    txt+="  |";
                }
			}
			System.out.println(txt);
			index++;
		}
	}
}

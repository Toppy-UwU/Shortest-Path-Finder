package algorPk;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private List<Node> nodes =  new ArrayList<>();
	private List<Edge> edges =  new ArrayList<>();
	
	public List<Node> getNodes(){
		return nodes;
	}
	
	public List<Edge> getEdges(){
		return edges;
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public void addEdge(Edge edge) {
		boolean added = false;
		for(Edge e: edges) {
			if(e.equals(edge)) {
				added = true;
				break;
			}
		}
		if(!added) {
			edges.add(edge);
		}
	}
	
	
	@Override
	public String toString() {
		String str = "";
		for(int i=0;i<=getNodes().size()-1;i++) {
			str += getNodes().get(i);
			for(int j = 0;j <= getEdges().size()-1;j++) {
				if(getEdges().get(j).hasNode(getNodes().get(i))) {
					if(getEdges().get(j).getNodeOne() == getNodes().get(i)) {
						str += "-> |" + getEdges().get(j).getNodeTwo() + "|" +getEdges().get(j).getWeight() + "|";
					}if(getEdges().get(j).getNodeTwo() == getNodes().get(i)) {
						str += "-> |" + getEdges().get(j).getNodeOne() + "|" +getEdges().get(j).getWeight() + "|";
					}
				}
			}
			str += "\n";
		}
		return str;
	}
}

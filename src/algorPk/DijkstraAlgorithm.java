package algorPk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
	private Graph graph;
	private Node start;
	
	private Map<Node,Node> previous;
	private Map<Node,Integer> distances;
	
	private PriorityQueue<Node> unvisited;
	private HashSet<Node> visited;
	
	public class NodeComparator implements Comparator<Node>{
		
		@Override
		public int compare(Node node1, Node node2) {
			return distances.get(node1)-distances.get(node2);
		}
		
	}
	
	public DijkstraAlgorithm(Graph graph, Node start) {
		this.graph = graph;
		this.start = start;
		previous = new HashMap<>();
		distances = new HashMap<>();
		visited = new HashSet<>();
		
		for(Node node : graph.getNodes()) {
			distances.put(node, Integer.MAX_VALUE);
		}
		
		distances.put(start, 0);
		visited.add(start);
		
	}
	
	public void run() {
		unvisited = new PriorityQueue<>(graph.getNodes().size(), new NodeComparator());
		
		for(Edge edge: getNeighbors(start)) {
			Node node = getAdjacent(edge,start);
			if(node == null) {
				continue;
			}
			distances.put(node, edge.getWeight());
			previous.put(node, start);
			unvisited.add(node);
		}
		
		while(!unvisited.isEmpty()) {
			Node node = unvisited.poll();
			int dist = distances.get(node);
			
			for(Edge neighbor:getNeighbors(node)) {
				Node adjacent = getAdjacent(neighbor, node);
				if(visited.contains(adjacent)) {
					continue;
				}
				
				int current_dist = distances.get(adjacent);
				int new_dist = dist+neighbor.getWeight();
				
				if(new_dist<current_dist) {
					distances.put(adjacent, new_dist);
					previous.put(adjacent, node);
					unvisited.add(adjacent);
				}
			}
			unvisited.remove(node);
			visited.add(node);
		}
	}
	
	private List<Edge> getNeighbors(Node node){
		List<Edge> neighbors = new ArrayList<>();
		for(Edge edge: graph.getEdges()) {
			if(edge.getNodeOne() == node || edge.getNodeTwo() == node) {
				neighbors.add(edge);
			}
		}
		return neighbors;
	}
	
	
	private Node getAdjacent(Edge edge, Node node) {
		if(edge.getNodeOne() != node && edge.getNodeTwo() != node) {
			return null;
		}
		if(node == edge.getNodeTwo()) {
			return edge.getNodeOne();
		}else {
			return edge.getNodeTwo();
		}
//		return node == edge.getNodeTwo()?edge.getNodeOne():edge.getNodeTwo();
	}
	
	public List<Node> getPath(Node node){
		List<Node> path = new ArrayList<>();
		Node current = node;
		path.add(current);
		while(current != start) {
			current = previous.get(current);
			path.add(current);
		}
		
		Collections.reverse(path);
		
		return path;
	}
	
	public String getFullPath(Node node,ArrayList<Edge> edge) {
		
		List<Node> path = new ArrayList<>();
		String strPath = "(";
		int sumPath = 0;
		
		Node current = node;
		path.add(current);
		while(current != start) {
			current = previous.get(current);
			path.add(current);
		}
		
		Collections.reverse(path);
		
		for(int i=0;i<path.size()-1;i++) {
			for(int j=0;j<edge.size();j++) {
				if((edge.get(j).getNodeOne() == path.get(i) 
					|| edge.get(j).getNodeTwo() == path.get(i)) 
					&& (edge.get(j).getNodeOne() == path.get(i+1) 
					|| edge.get(j).getNodeTwo() == path.get(i+1)) ) {
						strPath = strPath + path.get(i) +", "+ edge.get(j).getWeight()+", ";
						sumPath += edge.get(j).getWeight();
					}
			}
		}
		
		return strPath+node.toString()+")\nSum:"+sumPath;
	}
	
	public void setLineColor(Node node,ArrayList<Edge> edge, ArrayList<Boolean> line) {
		
		List<Node> path = new ArrayList<>();
		
		Node current = node;
		path.add(current);
		while(current != start) {
			current = previous.get(current);
			path.add(current);
		}
		
		Collections.reverse(path);
		
		for(int i=0;i<line.size();i++) {
			line.set(i, false);
		}
		
		
		for(int i=0;i<path.size()-1;i++) {
			for(int j=0;j<edge.size();j++) {
				if((edge.get(j).getNodeOne() == path.get(i) 
					|| edge.get(j).getNodeTwo() == path.get(i)) 
					&& (edge.get(j).getNodeOne() == path.get(i+1) 
					|| edge.get(j).getNodeTwo() == path.get(i+1)) ) {
						line.set(j, true);
					}
			}
		}
		
		
	}
}

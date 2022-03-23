package algorPk;

public class Edge {
	private Node one;
	private Node two;
	private int weight = 1;
	
	public Edge(Node one, Node two) {
		this.one = one;
		this.two = two;
	}
	
	public Edge(Node one, Node two, int weight) {
		this.one = one;
		this.two = two;
		this.weight = weight;
	}
	
	public Node getNodeOne() {
		return one;
	}
	
	public Node getNodeTwo() {
		return two;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public boolean hasNode(Node node) {
		return one == node || two == node;
	}
	
	public void setWeight(int x) {
		this.weight = x;
	}
	
}

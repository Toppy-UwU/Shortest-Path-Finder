package algorPk;

public class App {

	public static void main(String[] args) {
		Graph g = new Graph();
		
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		
		g.addNode(A);
		g.addNode(B);
		g.addNode(C);
		g.addNode(D);
		g.addNode(E);
		
		Edge newE = new Edge(A, B, 4000);
		
		newE.setWeight(5);
		
		g.addEdge(newE);
		g.addEdge(new Edge(A, C, 15));
		g.addEdge(new Edge(B, C, 5));
		g.addEdge(new Edge(B, D, 8));
		g.addEdge(new Edge(C, E, 3));
		g.addEdge(new Edge(D, E, 3));
		
		System.out.println(g.toString());
		DijkstraAlgorithm algor = new DijkstraAlgorithm(g, A);
		algor.run();
		System.out.println(algor.getPath(C));

	}
}


public abstract class Node {
	
	int[][] state;
	int pathCost;
	Node parent;
	int depth;
	int operation;	
	
	public Node(int[][] state, int pathCost, int depth, Node parent, int operation) {
		this.pathCost = pathCost;
		this.depth = depth;
		this.parent = parent;
		this.operation = operation;
	}
	
	public Node(int[][] state) {
		this.state = state;
		this.pathCost = 0;
		this.depth = 0;
		this.parent = null;
		this.operation = 0;
	}

}

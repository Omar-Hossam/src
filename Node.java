
public abstract class Node {
	
	int[][] state;
	int pathCost;
	Node parent;
	int depth;
	int operation;	
	
	public Node(int[][] state, int pathCost, int depth, Node parent, int operation) {
		this.state = state;
		this.pathCost = pathCost;
		this.depth = depth;
		this.parent = parent;
		this.operation = operation;
	}
	
//	0 = new or copy
//	1 = left
//	2 = right
//	3 = up
//	4 = down
	
	public Node(int[][] state) {
		this.state = state;
		this.pathCost = 0;
		this.depth = 0;
		this.parent = null;
		this.operation = 0;
	}

	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

}

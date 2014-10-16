public class Grid extends Node implements Comparable {
	
	private int score;
	private int heuristic = 0;
	
	public Grid(int[][] state, int pathCost, int depth, Node parent, int operation, int score) {
		super(state, pathCost, depth, parent, operation);
		this.score = score;
	}
	
	public Grid(int[][] state) {
		super(state);
		this.score = 0;
	}
		

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public int getCost() {
		return pathCost;
	}

	public void setCost(int cost) {
		this.pathCost = cost;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int[][] getGrid() {
		return state;
	}

	public void setGrid(int[][] grid) {
		this.state = grid;
	}
	
	//compares two grids together to see if they are the same or no
	@Override
	public int compareTo(Object arg0) {
		Grid temp = (Grid) arg0;
		boolean same = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (temp.state[i][j] != this.state[i][j]) {
					same = false;
					break;
				}
			}
			if (!same)
				break;
		}
		if (temp.score != this.score) {
			same = false;
		}
		if (same) {
			return 1;
		}
		return 0;

	}
}

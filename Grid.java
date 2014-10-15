public class Grid extends Node implements Comparable {
	
	private int score;
	private int heuristic = 0;
	//private int depth;
	
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

	public int clusterScore() {
		int level = 0;
		boolean bool;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (state[i][j] != 0) {

					for (int k = 0; k < 4; k++) {
						if (j != k) {
							if (state[i][j] == state[i][k]) {
								level += state[i][j];
								break;
							}
						}
					}

					for (int k = 0; k < 4; k++) {
						if (i != k) {
							if (state[i][j] == state[k][i]) {
								level += state[i][j];
								break;
							}
						}
					}
				}
			}
		}
		return level;
	}

	public int getHighest() {
		int h = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (state[i][j] >= h)
					return h;
			}
		}
		return h;
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

	public static void main(String[] s) {
		twozerofoureight game = new twozerofoureight();
		twozerofoureight game2 = new twozerofoureight();
		Grid g = game.GenGrid();
		Grid g2 = game2.GenGrid();
		game.PrintGrid(g);
		game2.PrintGrid(g2);
		System.out.println(g.compareTo(g2));
	}

}

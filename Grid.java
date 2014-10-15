public class Grid implements Comparable {
	
	private int[][] grid;
	private int score;
	private int cost = 0;
	private int heuristic = 0;
	private int depth;

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
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Grid(int[][] grid, int score) {
		this.grid = grid;
		this.score = score;
	}

	public int clusterScore() {
		int level = 0;
		boolean bool;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (grid[i][j] != 0) {

					for (int k = 0; k < 4; k++) {
						if (j != k) {
							if (grid[i][j] == grid[i][k]) {
								level += grid[i][j];
								break;
							}
						}
					}

					for (int k = 0; k < 4; k++) {
						if (i != k) {
							if (grid[i][j] == grid[k][i]) {
								level += grid[i][j];
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
				if (grid[i][j] >= h)
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
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	@Override
	public int compareTo(Object arg0) {
		Grid temp = (Grid) arg0;
		boolean same = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (temp.grid[i][j] != this.grid[i][j]) {
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

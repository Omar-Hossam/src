
public class Grid implements Comparable {
	private int [][] grid;
	private int score;
	private int cost = 0;
	private int heuristic = 0;

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
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				if (temp.grid[i][j] != this.grid[i][j]){
					same = false;
					break;
				}
			}
			if(!same)
				break;
		}
		if (temp.score != this.score){
			same = false;
		}
		if (same){
			return 1;
		}
		return 0;
			
	}
}


public class Grid implements Comparable {
	private int [][] grid;
	private int score;


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
	public static void main(String [] s){
		twozerofoureight game = new twozerofoureight();
		twozerofoureight game2 = new twozerofoureight();
		Grid g = game.GenGrid();
		Grid g2 = game2.GenGrid();
		game.PrintGrid(g);
		game2.PrintGrid(g2);
		System.out.println(g.compareTo(g2));
	}
}

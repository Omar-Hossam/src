import java.util.Random;

public class twozerofoureight extends GenericSearch {

	// Checks if grid has reached the goal tile.
	public boolean win(Grid grid, int win) {
		int[][] x = grid.getGrid();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (x[i][j] == win)
					return true;
			}
		}
		return false;
	}

	// Generates very first Grid, randomly adding two 2s to the grid.
	public Grid GenGrid() {
		Random rand = new Random();
		int n = rand.nextInt(4);
		int n2 = rand.nextInt(4);
		int[][] grid = new int[4][4];
		grid[n][n2] = 2;
		while (true) {
			int n3 = rand.nextInt(4);
			int n4 = rand.nextInt(4);
			if ((n != n3) || (n2 != n4)) {
				grid[n3][n4] = 2;
				break;
			}
		}
		return new Grid(grid);
	}

	// Checks if grid has reached a game over state. Meaning NO possible move
	// would ever change the state of that grid.
	public boolean GameOver(Grid oldGrid) {
		int[][] grid = oldGrid.getGrid();

		if (!Zeros(grid)) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int left = j - 1;
				int right = j + 1;
				int up = i - 1;
				int down = i + 1;

				if (left >= 0) {
					if (grid[i][j] == grid[i][left])
						return false;
				}
				if (right <= 3) {
					if (grid[i][j] == grid[i][right])
						return false;
				}
				if (up >= 0) {
					if (grid[i][j] == grid[up][j])
						return false;
				}
				if (down <= 3) {
					if (grid[i][j] == grid[down][j])
						return false;
				}
			}
		}
		return true;
	}

	// Checks if grid has no Zeros.
	public boolean Zeros(int[][] x) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (x[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	// Prints Grid.
	public void PrintGrid(Grid oldGrid) {
		int[][] grid = oldGrid.getGrid();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.printf("%5d ", grid[i][j]);
			}
			System.out.println();
		}
		System.out.println(oldGrid.getScore());
	}

	// Adds a new 2 Tile to the grid. Used after each move. 2s can only be added
	// to an empty corner, checking in a clockwise direction.
	public int[][] AddNew(int[][] grid) {
		int[][] grid2 = grid.clone();
		if (grid2[0][0] == 0) {
			grid2[0][0] = 2;
		} else if (grid2[0][3] == 0) {
			grid2[0][3] = 2;
		} else if (grid2[3][3] == 0) {
			grid2[3][3] = 2;
		} else if (grid2[3][0] == 0) {
			grid2[3][0] = 2;
		}

		return grid2;
	}

	// Plays a left move on the input grid.
	public Grid GoLeft(Grid oldGrid) {

		// This block creates a copy of the current node oldGrid.
		int[][] current = oldGrid.getGrid();
		int[][] old = new int[4][4];
		for (int i = 0; i < old.length; i++)
			for (int j = 0; j < old[i].length; j++)
				old[i][j] = current[i][j];

		Grid newGrid = new Grid(old, oldGrid.pathCost, oldGrid.depth + 1,
				oldGrid, 1, oldGrid.getScore());
		int score = newGrid.getScore();

		int[][] old2 = new int[4][4];
		for (int i = 0; i < old2.length; i++)
			for (int j = 0; j < old2[i].length; j++)
				old2[i][j] = current[i][j];

		/*
		 * Loops over grid adding adjacent tiles with same number together
		 * accoreding to the direction.
		 */
		int[][] grid = old2;
		boolean possible = false;
		for (int i = 0; i < 4; i++) {
			int[] bool = { 0, 0, 0, 0 };
			for (int j = 1; j < 4; j++) {
				if (grid[i][j] != 0) {
					int n = j;
					while (true) {
						//ignores first tile.
						if (n == 0) {
							break;
						}
						
						//ignores checked tiles.
						if (bool[n - 1] == 1) {
							break;
						}
						
						//merges tiles
						if (grid[i][n - 1] == grid[i][n]) {
							grid[i][n - 1] = grid[i][n - 1] * 2;
							score += grid[i][n - 1];
							grid[i][n] = 0;
							bool[n - 1] = 1;
							possible = true;
							break;
						}

						//ignores different tiles.
						if (grid[i][n - 1] != grid[i][n] && grid[i][n - 1] != 0) {
							break;
						}
						
						//moves tile if next to a zero tile.
						if (grid[i][n - 1] == 0) {
							grid[i][n - 1] = grid[i][n];
							grid[i][n] = 0;
							n--;
							possible = true;
						}
					}
				}
			}
		}
		
		if (!possible)
			return oldGrid;
		int[][] grid2 = AddNew(grid.clone());
		newGrid.setGrid(grid2);
		newGrid.setScore(score);
		newGrid.setCost((newGrid.getScore() - oldGrid.getScore()));
		return newGrid;
	}

	// Plays a right move on the input grid.
	public Grid GoRight(Grid oldGrid) {

		int[][] current = oldGrid.getGrid();
		int[][] old = new int[4][4];
		for (int i = 0; i < old.length; i++)
			for (int j = 0; j < old[i].length; j++)
				old[i][j] = current[i][j];
		Grid newGrid = new Grid(old, oldGrid.pathCost, oldGrid.depth + 1,
				oldGrid, 2, oldGrid.getScore());
		int score = newGrid.getScore();

		int[][] old2 = new int[4][4];
		for (int i = 0; i < old2.length; i++)
			for (int j = 0; j < old2[i].length; j++)
				old2[i][j] = current[i][j];

		int[][] grid = old2;

		boolean possible = false;
		for (int i = 0; i < 4; i++) {
			int[] bool = { 0, 0, 0, 0 };
			for (int j = 2; j >= 0; j--) {
				if (grid[i][j] != 0) {
					int n = j;
					while (true) {
						if (n == 3) {
							break;
						}

						if (bool[n + 1] == 1) {
							break;
						}

						if (grid[i][n + 1] == grid[i][n]) {
							grid[i][n + 1] = grid[i][n + 1] * 2;
							score += grid[i][n + 1];
							grid[i][n] = 0;
							bool[n + 1] = 1;
							possible = true;
							break;
						}

						if (grid[i][n + 1] != grid[i][n] && grid[i][n + 1] != 0) {
							break;
						}

						if (grid[i][n + 1] == 0) {
							grid[i][n + 1] = grid[i][n];
							grid[i][n] = 0;
							possible = true;
							n++;
						}
					}
				}
			}
		}
		if (!possible)
			return oldGrid;
		grid = AddNew(grid);
		newGrid.setGrid(grid);
		newGrid.setScore(score);
		newGrid.setCost((newGrid.getScore() - oldGrid.getScore()));
		return newGrid;
	}

	// Plays an up move on the input grid.
	public Grid GoUp(Grid oldGrid) {

		int[][] current = oldGrid.getGrid();
		int[][] old = new int[4][4];
		for (int i = 0; i < old.length; i++)
			for (int j = 0; j < old[i].length; j++)
				old[i][j] = current[i][j];
		Grid newGrid = new Grid(old, oldGrid.pathCost, oldGrid.depth + 1,
				oldGrid, 3, oldGrid.getScore());
		int score = newGrid.getScore();

		int[][] old2 = new int[4][4];
		for (int i = 0; i < old2.length; i++)
			for (int j = 0; j < old2[i].length; j++)
				old2[i][j] = current[i][j];

		int[][] grid = old2;

		boolean possible = false;
		for (int i = 0; i < 4; i++) {
			int[] bool = { 0, 0, 0, 0 };
			for (int j = 1; j < 4; j++) {
				if (grid[j][i] != 0) {
					int n = j;
					while (true) {
						if (n == 0) {
							break;
						}

						if (bool[n - 1] == 1) {
							break;
						}

						if (grid[n - 1][i] == grid[n][i]) {
							grid[n - 1][i] = grid[n - 1][i] * 2;
							score += grid[n - 1][i];
							grid[n][i] = 0;
							bool[n - 1] = 1;
							possible = true;
							break;
						}

						if (grid[n - 1][i] != grid[n][i] && grid[n - 1][i] != 0) {
							break;
						}

						if (grid[n - 1][i] == 0) {
							grid[n - 1][i] = grid[n][i];
							grid[n][i] = 0;
							possible = true;
							n--;
						}
					}
				}
			}
		}
		if (!possible)
			return oldGrid;
		grid = AddNew(grid);
		newGrid.setGrid(grid);
		newGrid.setScore(score);
		newGrid.setCost((newGrid.getScore() - oldGrid.getScore()));
		return newGrid;
	}

	// Plays a down move on the input grid.
	public Grid GoDown(Grid oldGrid) {

		int[][] current = oldGrid.getGrid();
		int[][] old = new int[4][4];
		for (int i = 0; i < old.length; i++)
			for (int j = 0; j < old[i].length; j++)
				old[i][j] = current[i][j];

		Grid newGrid = new Grid(old, oldGrid.pathCost, oldGrid.depth + 1,
				oldGrid, 4, oldGrid.getScore());
		int score = newGrid.getScore();

		int[][] old2 = new int[4][4];
		for (int i = 0; i < old2.length; i++)
			for (int j = 0; j < old2[i].length; j++)
				old2[i][j] = current[i][j];

		int[][] grid = old2;

		boolean possible = false;
		for (int i = 0; i < 4; i++) {
			int[] bool = { 0, 0, 0, 0 };
			for (int j = 2; j >= 0; j--) {
				if (grid[j][i] != 0) {
					int n = j;
					while (true) {
						if (n == 3) {
							break;
						}

						if (bool[n + 1] == 1) {
							break;
						}

						if (grid[n + 1][i] == grid[n][i]) {
							grid[n + 1][i] = grid[n + 1][i] * 2;
							score += grid[n + 1][i];
							grid[n][i] = 0;
							bool[n + 1] = 1;
							possible = true;
							break;
						}

						if (grid[n + 1][i] != grid[n][i] && grid[n + 1][i] != 0) {
							break;
						}

						if (grid[n + 1][i] == 0) {
							grid[n + 1][i] = grid[n][i];
							grid[n][i] = 0;
							possible = true;
							n++;
						}
					}
				}
			}
		}
		if (!possible)
			return oldGrid;
		grid = AddNew(grid);
		newGrid.setGrid(grid);
		newGrid.setScore(score);
		newGrid.setCost((newGrid.getScore() - oldGrid.getScore()));
		return newGrid;
	}

}
